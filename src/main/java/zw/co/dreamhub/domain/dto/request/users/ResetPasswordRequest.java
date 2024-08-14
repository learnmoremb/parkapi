package zw.co.dreamhub.domain.dto.request.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 19/9/2023
 */
public record ResetPasswordRequest(
        @NotNull
        @NotBlank
        @Email
        String username
) {
}
