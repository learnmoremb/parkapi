package zw.co.dreamhub.domain.mappers.driver;

import org.mapstruct.*;
import zw.co.dreamhub.domain.dto.request.driver.DriverRequest;
import zw.co.dreamhub.domain.dto.request.passenger.PassengerRequest;
import zw.co.dreamhub.domain.models.driver.Driver;
import zw.co.dreamhub.domain.models.passenger.Passenger;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/04
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DriverMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Driver partialUpdate(DriverRequest request, @MappingTarget Driver driver);
}
