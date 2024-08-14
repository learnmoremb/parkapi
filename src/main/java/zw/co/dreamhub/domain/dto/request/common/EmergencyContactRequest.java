package zw.co.dreamhub.domain.dto.request.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import zw.co.dreamhub.domain.dto.request.users.UserDetailRequest;
import zw.co.dreamhub.domain.models.enums.Relationship;
import zw.co.dreamhub.domain.models.enums.Status;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/18
 */
public record EmergencyContactRequest(
        @NotNull @NotBlank Relationship relationship,
        String otherDescription,
        @NotNull Status status,
        @NotNull UserDetailRequest contact) {
}
