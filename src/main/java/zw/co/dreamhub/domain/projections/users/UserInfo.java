package zw.co.dreamhub.domain.projections.users;

import zw.co.dreamhub.domain.models.enums.UserStatus;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Projection for {@link zw.co.dreamhub.domain.models.users.User}
 */
public interface UserInfo {
    String getId();

    ZonedDateTime getDateCreated();

    ZonedDateTime getLastUpdated();

    String getUsername();

    String getCountry();

    UserStatus getStatus();

    UserDetailInfo getDetail();

    Set<RoleInfo> getRoles();
}