package zw.co.dreamhub.domain.dto.request.users;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 19/9/2023
 */
public record RefreshTokenRequest(
        @NotNull UUID token
) {
}
