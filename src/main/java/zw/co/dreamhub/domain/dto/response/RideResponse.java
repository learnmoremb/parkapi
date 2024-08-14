package zw.co.dreamhub.domain.dto.response;

import zw.co.dreamhub.domain.models.enums.RideStatus;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/21
 */
public record RideResponse(
        RideStatus rideStatus,
        String driverId,
        String passengerIds,
        String vehicleId
) {
}
