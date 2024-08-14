package zw.co.dreamhub.domain.dto.request.ride;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/08
 */
public record NotificationRequest(
        @NotNull String title,
        @NotNull String body,
        @NotNull Set<String> deviceTokens
) {
}
