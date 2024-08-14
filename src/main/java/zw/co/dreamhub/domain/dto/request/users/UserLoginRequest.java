package zw.co.dreamhub.domain.dto.request.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 6/12/2023
 */
public record UserLoginRequest(

        @NotNull
        @NotBlank
        @Email
        String username,

        @NotNull
        @NotBlank
        @Size(min = 5)
        String password,

        String deviceToken
) {
}
