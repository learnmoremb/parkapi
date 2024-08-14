package zw.co.dreamhub.domain.dto.request.driver;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import zw.co.dreamhub.domain.models.enums.Status;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/16
 */

public record VehicleCategoryRequest(
        @NotNull @NotBlank String name,
        @NotNull @NotBlank String description,
        @NotNull Status status
) {
}
