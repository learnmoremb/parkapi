package zw.co.dreamhub.domain.mappers.driver;

import org.mapstruct.*;
import zw.co.dreamhub.domain.dto.request.driver.VehicleRequest;
import zw.co.dreamhub.domain.models.driver.Vehicle;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/14
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VehicleMapper {
    Vehicle toEntity(VehicleRequest vehicleRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Vehicle partialUpdate(VehicleRequest vehicleRequest, @MappingTarget Vehicle vehicle);

}
