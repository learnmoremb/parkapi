package zw.co.dreamhub.domain.mappers.passenger;

import org.mapstruct.*;
import zw.co.dreamhub.domain.dto.request.passenger.PassengerRequest;
import zw.co.dreamhub.domain.models.passenger.Passenger;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 26/12/2023
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PassengerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Passenger partialUpdate(PassengerRequest request, @MappingTarget Passenger passenger);
}
