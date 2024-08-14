package zw.co.dreamhub.domain.mappers.driver;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import zw.co.dreamhub.domain.dto.request.driver.DriversLicenseRequest;
import zw.co.dreamhub.domain.dto.request.driver.InsuranceRequest;
import zw.co.dreamhub.domain.models.driver.DriversLicense;
import zw.co.dreamhub.domain.models.driver.Insurance;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/15
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DriversLicenseMapper {
    DriversLicense toEntity(DriversLicenseRequest driversLicenseRequest);

}
