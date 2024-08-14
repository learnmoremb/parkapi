package zw.co.dreamhub.domain.dto.request.users;

import jakarta.validation.constraints.NotNull;
import zw.co.dreamhub.domain.models.enums.UserRole;
import zw.co.dreamhub.domain.models.users.Role;

import java.io.Serializable;

/**
 * DTO for {@link Role}
 */
public record RoleRequest(@NotNull UserRole type) implements Serializable {
}