package zw.co.dreamhub.domain.mappers.common;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import zw.co.dreamhub.domain.dto.request.common.AddressRequest;
import zw.co.dreamhub.domain.models.common.Address;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/08
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {
    Address toEntity(AddressRequest request);
}
