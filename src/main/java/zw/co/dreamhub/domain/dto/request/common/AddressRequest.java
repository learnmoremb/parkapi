package zw.co.dreamhub.domain.dto.request.common;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/17
 */
public record AddressRequest(
        @NotNull @NotBlank String street,
        @NotNull @NotBlank String suburb,
        @NotNull @NotBlank String country
) {
}
