package zw.co.dreamhub.domain.mappers.common;

import org.mapstruct.*;
import zw.co.dreamhub.domain.dto.request.common.AmountRequest;
import zw.co.dreamhub.domain.models.common.Amount;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AmountMapper {
    Amount toEntity(AmountRequest amountRequest);

    AmountRequest toDto(Amount amount);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Amount partialUpdate(AmountRequest amountRequest, @MappingTarget Amount amount);
}