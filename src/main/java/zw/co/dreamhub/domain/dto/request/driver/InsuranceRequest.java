package zw.co.dreamhub.domain.dto.request.driver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.dreamhub.domain.models.enums.InsurancePolicyType;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/14
 */
public record InsuranceRequest(@NotNull @NotBlank String name,
                               @NotNull InsurancePolicyType type,
                               @NotNull
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                               ZonedDateTime expiryDate,
                               @NotNull @NotBlank String vehicleId
) implements Serializable {
}
