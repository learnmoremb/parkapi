package zw.co.dreamhub.domain.mappers.driver;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import zw.co.dreamhub.domain.dto.request.common.EmergencyContactRequest;
import zw.co.dreamhub.domain.dto.request.driver.DriversLicenseRequest;
import zw.co.dreamhub.domain.models.common.EmergencyContact;
import zw.co.dreamhub.domain.models.driver.DriversLicense;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/18
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmergencyContactMapper {
    EmergencyContact toEntity(EmergencyContactRequest emergencyContactRequest);

}
