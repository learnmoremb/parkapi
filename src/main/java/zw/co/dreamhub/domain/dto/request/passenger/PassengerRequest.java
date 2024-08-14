package zw.co.dreamhub.domain.dto.request.passenger;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import zw.co.dreamhub.domain.dto.request.common.AddressRequest;
import zw.co.dreamhub.domain.models.enums.Status;

import java.util.Set;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/20
 */
public record PassengerRequest(
        @NotNull String userId,
        @NotEmpty Set<@NotNull AddressRequest> addresses,
        @NotNull Status status,
        @NotNull String statusDescription
) {
}
