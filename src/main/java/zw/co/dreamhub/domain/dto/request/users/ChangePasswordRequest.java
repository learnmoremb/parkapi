package zw.co.dreamhub.domain.dto.request.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 19/9/2023
 */
public record ChangePasswordRequest(
        @NotNull
        @NotBlank
        @Size(min = 5)
        String oldPassword,

        @NotNull
        @NotBlank
        @Size(min = 5)
        String newPassword,

        @NotNull
        @NotBlank
        @Size(min = 5)
        String confirmPassword
) {
}
