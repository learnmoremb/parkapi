package zw.co.dreamhub.domain.dto.request.ride;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import zw.co.dreamhub.domain.dto.request.common.AddressRequest;
import zw.co.dreamhub.domain.dto.request.common.AmountRequest;
import zw.co.dreamhub.domain.models.ride.PaymentType;

import java.util.Set;
import java.util.UUID;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/07
 */
public record RideRequest(
        @NotNull UUID rideTypeId,
        @NotNull RideDetails details,
        @NotNull PaymentDetailsRequest payment
) {

    public record RideDetails(
            @NotNull @NotBlank String country,
            @NotNull AddressRequest from,
            @NotNull AddressRequest to,
            @NotEmpty Set<@NotNull UUID> passengers,
            @NotNull @NotBlank String notes

    ) {
    }

    public record PaymentDetailsRequest(
            @NotNull PaymentType type,
            @NotNull AmountRequest amount,
            @NotNull @NotBlank String account,
            @Email @NotBlank @NotNull String email
    ) {
    }
}
