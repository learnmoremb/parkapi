package zw.co.dreamhub.domain.dto.request.ride;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/08
 */
public record RideAcceptanceRequest(
        @NotNull UUID rideId,
        @NotNull UUID driverId,
        @NotNull UUID vehicleId
) {}
