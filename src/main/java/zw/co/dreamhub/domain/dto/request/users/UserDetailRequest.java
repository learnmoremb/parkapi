package zw.co.dreamhub.domain.dto.request.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import zw.co.dreamhub.domain.models.users.UserDetail;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link UserDetail}
 */
public record UserDetailRequest(@NotNull @NotBlank String firstName,
                                String middleNames,
                                @NotNull @NotBlank String lastName,
                                @NotNull @Email @NotBlank String email,
                                @NotEmpty Set<@NotNull PhoneNumberRequest> phoneNumbers) implements Serializable {
}