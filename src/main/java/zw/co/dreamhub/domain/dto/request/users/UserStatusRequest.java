package zw.co.dreamhub.domain.dto.request.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import zw.co.dreamhub.domain.models.enums.UserStatus;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 20/9/2023
 */
public record UserStatusRequest(

        @NotNull
        @NotBlank
        @Email
        String username,

        @NotNull
        UserStatus status
) {
}
