package zw.co.dreamhub.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.config.env.InfoEnv;
import zw.co.dreamhub.domain.dto.response.users.AuthResponse;
import zw.co.dreamhub.domain.models.enums.UserRole;
import zw.co.dreamhub.domain.models.users.Role;
import zw.co.dreamhub.domain.models.users.TokenRefresh;
import zw.co.dreamhub.domain.models.users.User;
import zw.co.dreamhub.domain.repositories.users.TokenRefreshRepository;
import zw.co.dreamhub.domain.repositories.users.UserRepository;
import zw.co.dreamhub.exception.NotFoundException;
import zw.co.dreamhub.security.jwt.JwtUtils;
import zw.co.dreamhub.utils.StringUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 17/3/2023
 */

@Service
@RequiredArgsConstructor
public class AuthUtils {

    private final UserRepository repository;
    private final TokenRefreshRepository tokenRefreshRepository;
    private final JwtUtils jwtUtils;

    private final InfoEnv env;

    public String getUsername() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUsername();
    }

    public User getUser() {
        return repository.findByUsername(this.getUsername()).orElseThrow(() -> new NotFoundException(StringUtils.notFound("user")));
    }

    public Boolean isAuthorised(Set<UserRole> roles) {
        Set<UserRole> userRoles = this.getUser().getRoles().stream().map(Role::getType).collect(Collectors.toSet());
        return userRoles.containsAll(roles);
    }

    public AuthResponse authenticate(User user) {

        TokenRefresh refreshToken = new TokenRefresh(user, UUID.randomUUID(),
                ZonedDateTime
                        .now(ZoneId.systemDefault())
                        .plusMinutes(env.security().jwt().jwtRefreshExpirationMs() / 60000)
        );
        tokenRefreshRepository.save(refreshToken);

        return new AuthResponse(
                new AuthResponse.Token(
                        jwtUtils.generateTokenFromUsername(user.getUsername()),
                        env.security().jwt().jwtExpirationMs(),
                        StringUtils.ACCESS_TOKEN_TYPE,
                        refreshToken.getToken().toString(),
                        env.security().jwt().jwtRefreshExpirationMs()
                ),
                user.getRoles().
                        stream()
                        .map(r -> new AuthResponse.Role(r.getType()))
                        .collect(Collectors.toSet())
        );
    }


}
