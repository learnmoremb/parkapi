package zw.co.dreamhub.domain.dto.request.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link zw.co.dreamhub.domain.models.common.PhoneNumber}
 */
public record PhoneNumberRequest(@NotNull @Size(min = 12, max = 12) @NotBlank String number) implements Serializable {
}