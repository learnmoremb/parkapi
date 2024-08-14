package zw.co.dreamhub.domain.dto.request.driver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import zw.co.dreamhub.domain.models.driver.VehicleCategory;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/14
 */
public record VehicleRequest(
        @NotNull String make,
        @NotNull String model,
        @NotNull long year,
        @NotNull String color,
        @NotNull String description,
        @NotNull long capacity,
        @NotNull String registration,
        @NotNull String categoryName,
        @NotNull double engineSize
) {
}
