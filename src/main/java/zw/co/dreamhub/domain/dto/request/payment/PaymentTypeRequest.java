package zw.co.dreamhub.domain.dto.request.payment;

import jakarta.validation.constraints.NotNull;
import zw.co.dreamhub.domain.models.enums.Status;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/08
 */
public record PaymentTypeRequest(
      @NotNull String name,
      @NotNull  String description,
      @NotNull  Status status
) {
}
