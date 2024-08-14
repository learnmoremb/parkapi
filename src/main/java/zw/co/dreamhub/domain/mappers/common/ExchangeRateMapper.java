package zw.co.dreamhub.domain.mappers.common;

import org.mapstruct.*;
import zw.co.dreamhub.domain.dto.request.common.ExchangeRateRequest;
import zw.co.dreamhub.domain.models.common.ExchangeRate;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/18
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExchangeRateMapper {
    ExchangeRate toEntity(ExchangeRateRequest exchangeRateRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ExchangeRate partialUpdate(ExchangeRateRequest exchangeRateRequest, @MappingTarget ExchangeRate exchangeRate);
}
