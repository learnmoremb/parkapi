package zw.co.dreamhub.domain.projections.users;

import zw.co.dreamhub.domain.models.enums.UserRole;

/**
 * Projection for {@link zw.co.dreamhub.domain.models.users.Role}
 */
public interface RoleInfo {
    UserRole getType();
}