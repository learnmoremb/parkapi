package zw.co.dreamhub.domain.dto.request.driver;

import jakarta.validation.constraints.NotNull;
import zw.co.dreamhub.domain.dto.request.common.AddressRequest;
import zw.co.dreamhub.domain.dto.request.users.UserDetailRequest;

import java.util.Set;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/17
 */
public record DriverRequest(
        @NotNull String userId,
        Set<@NotNull String> vehicleIds,
        @NotNull Set<String> licenseIds,
        @NotNull AddressRequest addressRequest,
        @NotNull String statusDescription,
        UserDetailRequest userDetailRequest

) {
}
