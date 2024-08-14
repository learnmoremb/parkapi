package zw.co.dreamhub.domain.mappers.driver;

import org.mapstruct.*;
import zw.co.dreamhub.domain.dto.request.driver.VehicleCategoryRequest;
import zw.co.dreamhub.domain.dto.request.driver.VehicleRequest;
import zw.co.dreamhub.domain.models.driver.Vehicle;
import zw.co.dreamhub.domain.models.driver.VehicleCategory;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VehicleCategoryMapper {
    VehicleCategory toEntity(VehicleCategoryRequest vehicleCategoryRequest);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VehicleCategory partialUpdate(VehicleCategoryRequest vehicleCategoryRequest, @MappingTarget VehicleCategory vehicleCategory);

}
