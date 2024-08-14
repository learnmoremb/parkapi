package zw.co.dreamhub.domain.projections.users;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Projection for {@link zw.co.dreamhub.domain.models.users.UserDetail}
 */
public interface UserDetailInfo {
    ZonedDateTime getDateCreated();

    ZonedDateTime getLastUpdated();

    String getFirstName();

    String getMiddleNames();

    String getLastName();

    String getEmail();

    Set<PhoneNumberInfo> getPhoneNumbers();
}