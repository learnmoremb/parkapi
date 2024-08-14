package zw.co.dreamhub.domain.dto.request.ride;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.dreamhub.domain.dto.request.common.AddressRequest;
import zw.co.dreamhub.domain.models.enums.Currency;
import zw.co.dreamhub.domain.models.enums.RideStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/21
 */
public record BookRideRequest(
        @NotNull UUID rideId,
        @NotNull @NotBlank String accountNumber,
        @Email @NotNull @NotBlank String email,
        @NotNull BigDecimal rideFee,
        @NotNull Currency currency,
        @NotNull String paymentTypeId,
        @NotNull AddressRequest pickUpAddress,
        @NotNull AddressRequest dropOffAddress,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        ZonedDateTime pickUpTime,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        ZonedDateTime dropOfTime
) {
}
