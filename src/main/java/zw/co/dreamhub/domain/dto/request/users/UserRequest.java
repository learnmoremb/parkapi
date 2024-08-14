package zw.co.dreamhub.domain.dto.request.users;

import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO for {@link zw.co.dreamhub.domain.models.users.User}
 */
public record UserRequest(

        @NotNull
        @NotBlank
        @Size(min = 5)
        String password,
        @NotNull @NotBlank String country,
        @NotNull
        @NotBlank
        @Email
        String username,
        @NotNull UserDetailRequest detail) implements Serializable {
}