package zw.co.dreamhub.domain.dto.request.common;

import jakarta.validation.constraints.NotNull;


/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/18
 */
public record ExchangeRateRequest(
        @NotNull AmountRequest amount
) {
}
