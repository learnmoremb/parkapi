package zw.co.dreamhub.services.users;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.config.env.InfoEnv;
import zw.co.dreamhub.domain.dto.request.MessengerRequest;
import zw.co.dreamhub.domain.dto.request.users.*;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.dto.response.PaginatedResponse;
import zw.co.dreamhub.domain.mappers.users.UserMapper;
import zw.co.dreamhub.domain.models.driver.Driver;
import zw.co.dreamhub.domain.models.enums.MessageType;
import zw.co.dreamhub.domain.models.enums.UserRole;
import zw.co.dreamhub.domain.models.enums.UserStatus;
import zw.co.dreamhub.domain.models.passenger.Passenger;
import zw.co.dreamhub.domain.models.users.Role;
import zw.co.dreamhub.domain.models.users.TokenRefresh;
import zw.co.dreamhub.domain.models.users.User;
import zw.co.dreamhub.domain.projections.users.UserInfo;
import zw.co.dreamhub.domain.repositories.driver.DriverRepository;
import zw.co.dreamhub.domain.repositories.passenger.PassengerRepository;
import zw.co.dreamhub.domain.repositories.users.TokenRefreshRepository;
import zw.co.dreamhub.domain.repositories.users.UserRepository;
import zw.co.dreamhub.exception.AlreadyExistException;
import zw.co.dreamhub.exception.AuthException;
import zw.co.dreamhub.exception.NotFoundException;
import zw.co.dreamhub.exception.ValidationException;
import zw.co.dreamhub.security.services.AuthUtils;
import zw.co.dreamhub.security.services.PasswordService;
import zw.co.dreamhub.services.MessengerService;
import zw.co.dreamhub.utils.ResponseUtils;
import zw.co.dreamhub.utils.StringUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Optional;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 18/9/2023
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenRefreshRepository tokenRefreshRepository;
    private final MessengerService messengerService;
    private final AuthUtils authUtils;
    private final InfoEnv env;
    private final PasswordService passwordService;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    private static final String NAME = "User";
    private final DriverRepository driverRepository;
    private final PassengerRepository passengerRepository;

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public ApiResponse<String> updateStatus(UserStatusRequest request) {
        Optional<User> user = userRepository.findByUsername(request.username());
        return user.map(u -> {
            u.setStatus(request.status());
            userRepository.save(u);
            return ResponseUtils.success(
                    StringUtils.SUCCESS
            );
        }).orElseThrow(() -> new NotFoundException(StringUtils.notFound(NAME)));
    }

    @Transactional
    public ApiResponse<String> register(UserRequest request, UserRole role) {
        Optional<User> user = userRepository.findByUsername(request.username());
        if (user.isPresent()) {
            throw new AlreadyExistException(StringUtils.alreadyExists(NAME));
        } else {
            User regUser = userMapper.toEntity(request);

            regUser.getRoles().add(new Role(role));
            regUser.setPassword(encoder.encode(request.password()));
            userRepository.save(regUser);

            if (role.equals(UserRole.DRIVER)) {
                Driver driver = new Driver(regUser);
                driverRepository.save(driver);
            } else if (role.equals(UserRole.PASSENGER)) {
                Passenger passenger = new Passenger(regUser);
                passengerRepository.save(passenger);
            }

            return ResponseUtils.created(StringUtils.SUCCESS);
        }
    }

    public ApiResponse<Object> login(UserLoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.username());
        if (user.isPresent()) {
            if (user.get().getStatus().equals(UserStatus.ACTIVE)) {
                if (encoder.matches(request.password(), user.get().getPassword())) {
                    // persist notification token if it's available
                    if (request.deviceToken() != null) {
                        user.get().getDeviceTokens().add(request.deviceToken());
                        userRepository.save(user.get());
                    }
                    // respond
                    return ResponseUtils.success(authUtils.authenticate(user.get()));
                } else {
                    throw new AuthException("Bad credentials");
                }
            } else {
                throw new AuthException("Account locked");
            }
        } else {
            throw new AuthException(StringUtils.notFound(NAME));
        }
    }

    public ApiResponse<Object> refreshToken(RefreshTokenRequest request) {
        Optional<TokenRefresh> tokenRefresh = tokenRefreshRepository.findByTokenAndIsValidTrue(request.token());
        if (tokenRefresh.isPresent()) {
            ZonedDateTime now = ZonedDateTime
                    .now(ZoneId.systemDefault());

            if (tokenRefresh.get().getExpiryDate().isBefore(now) || tokenRefresh.get().getExpiryDate().isEqual(now)) {
                tokenRefresh.get().setIsValid(false);
                tokenRefreshRepository.save(tokenRefresh.get());
                return ResponseUtils.success(tokenRefresh.get().getUser());
            } else {
                throw new ValidationException("Token expired");
            }
        } else {
            throw new NotFoundException(StringUtils.notFound("Token"));
        }

    }

    public ApiResponse<Object> getUser(String username) {
        Optional<UserInfo> user = userRepository.findByUsernameIgnoreCase(username);
        if (user.isPresent()) {
            return ResponseUtils.success(
                    user.get()
            );
        } else {
            throw new NotFoundException(StringUtils.notFound(NAME));
        }
    }

    public ApiResponse<String> changePassword(ChangePasswordRequest request) {
        Optional<User> user = userRepository.findByUsername(authUtils.getUsername());
        return user.map(u -> {
            if (request.newPassword().equals(request.oldPassword())) {
                throw new ValidationException(StringUtils.SAME_PASSWORD);
            } else {
                if (request.newPassword().equals(request.confirmPassword())) {
                    u.setPassword(encoder.encode(request.newPassword()));
                    userRepository.save(u);
                    return ResponseUtils.success(
                            StringUtils.SUCCESS
                    );
                } else {
                    throw new ValidationException(StringUtils.DIFFERENT_PASSWORDS);
                }
            }
        }).orElseThrow(() ->
                new NotFoundException(StringUtils.notFound(NAME))
        );

    }

    public ApiResponse<String> resetPassword(ResetPasswordRequest request) {
        Optional<User> user = userRepository.findByUsername(request.username());
        return user.map(u -> {
                    String password = passwordService.randomPassword();
                    u.setPassword(encoder.encode(password));
                    userRepository.save(u);
                    messengerService.send(new MessengerRequest(
                            MessageType.EMAIL,
                            u.getDetail().getEmail(),
                            StringUtils.passwordMessage(password),
                            "Password Reset",
                            env.app().name()
                    ));
                    return ResponseUtils.success(
                            StringUtils.SUCCESS
                    );
                })
                .orElseThrow(() -> new NotFoundException(StringUtils.notFound(NAME)));
    }

    public ApiResponse<PaginatedResponse<UserInfo>> getUsersByRole(
            UserRole role,
            int pageNumber,
            int pageCount
    ) {
        Page<UserInfo> data = userRepository.findByRoles_TypeOrderByUsername(role, PageRequest.of(pageNumber, pageCount));
        return ResponseUtils.success(
                ResponseUtils.pageResponse(data, pageNumber)
        );
    }

    // todo : add functionality to add another role to a user

    public ApiResponse<String> addRoleToUser(String username, Role role) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.getRoles().add(role);
        userRepository.save(user);
        return ResponseUtils.success(
                StringUtils.SUCCESS
        );
    }

}
