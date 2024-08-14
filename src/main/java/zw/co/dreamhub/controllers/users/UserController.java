package zw.co.dreamhub.controllers.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zw.co.dreamhub.domain.dto.request.users.*;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.dto.response.PaginatedResponse;
import zw.co.dreamhub.domain.models.enums.UserRole;
import zw.co.dreamhub.domain.projections.users.UserInfo;
import zw.co.dreamhub.services.users.UserService;
import zw.co.dreamhub.utils.ResponseUtils;


/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 19/9/2023
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "user & auth management")
public class UserController {

    private final UserService service;

    @PostMapping(value = "${info.url.secured}/users/register/admin", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Operation(summary = "Register Admin", description = "Registers admin user")
    public ResponseEntity<ApiResponse<String>> registerAdminUser(@Valid @RequestBody UserRequest request) {
        var response = service.register(request, UserRole.SUPER_ADMIN);
        return ResponseUtils.respond(response);
    }

    @PutMapping(value = "${info.url.secured}/users/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @Operation(summary = "Update User", description = "Update user status")
    public ResponseEntity<ApiResponse<String>> updateUserStatus(@Valid @RequestBody UserStatusRequest request) {
        var response = service.updateStatus(request);
        return ResponseUtils.respond(response);
    }

    @PostMapping(value = "${info.url.unSecured}/users/register/user", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Register User", description = "Registers users")
    public ResponseEntity<ApiResponse<String>> registerUser(@Valid @RequestBody UserRequest request,
                                                            @RequestParam(name = "role") UserRole role) {
        if (role.equals(UserRole.SUPER_ADMIN)) {
            return ResponseUtils.respond(
                    ResponseUtils.badRequest("Action not allowed", HttpStatus.FORBIDDEN.value())
            );
        } else {
            var response = service.register(request, role);
            return ResponseUtils.respond(response);
        }
    }

    @PostMapping(value = "${info.url.unSecured}/users/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Login", description = "Authenticates users")
    public ResponseEntity<ApiResponse<Object>> login(@Valid @RequestBody UserLoginRequest request) {
        var response = service.login(request);
        return ResponseUtils.respond(response);
    }

    @PostMapping(value = "${info.url.unSecured}/users/refresh", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Refresh Token", description = "Refreshes auth session")
    public ResponseEntity<ApiResponse<Object>> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        var response = service.refreshToken(request);
        return ResponseUtils.respond(response);
    }

    @GetMapping(value = "${info.url.secured}/user",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "User Details", description = "Gets authenticated user details")
    public ResponseEntity<ApiResponse<Object>> getUser(@RequestParam(name = "username") String username) {
        var response = service.getUser(username);
        return ResponseUtils.respond(response);
    }

    @GetMapping(value = "${info.url.secured}/users",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Users", description = "Gets all users by role")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<PaginatedResponse<UserInfo>>> getUsersByRole(
            @RequestParam(name = "type") UserRole role,
            @RequestParam(name = "pageNumber") int pageNumber,
            @RequestParam(name = "pageCount") int pageCount
    ) {
        var response = service.getUsersByRole(role, pageNumber, pageCount);
        return ResponseUtils.respond(response);
    }

    @PutMapping(value = "${info.url.secured}/users/change-password", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Change password", description = "Change user password")
    public ResponseEntity<ApiResponse<String>> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        var response = service.changePassword(request);
        return ResponseUtils.respond(response);
    }

    @PostMapping(value = "${info.url.unSecured}/users/reset-password", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Reset Password", description = "Reset user password")
    public ResponseEntity<ApiResponse<String>> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        var response = service.resetPassword(request);
        return ResponseUtils.respond(response);
    }

}
