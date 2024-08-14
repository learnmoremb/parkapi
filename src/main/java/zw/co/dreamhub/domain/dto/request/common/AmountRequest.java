package zw.co.dreamhub.domain.dto.request.common;

import jakarta.validation.constraints.NotNull;
import zw.co.dreamhub.domain.models.enums.Currency;

import java.math.BigDecimal;

/**
 * DTO for {@link zw.co.dreamhub.domain.models.common.Amount}
 */
public record AmountRequest(@NotNull BigDecimal value,
                            @NotNull Currency currency) {
}