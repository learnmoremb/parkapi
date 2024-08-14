package zw.co.dreamhub.domain.mappers.payment;

import org.mapstruct.*;
import zw.co.dreamhub.domain.dto.request.driver.VehicleCategoryRequest;
import zw.co.dreamhub.domain.dto.request.payment.PaymentTypeRequest;
import zw.co.dreamhub.domain.models.driver.VehicleCategory;
import zw.co.dreamhub.domain.models.ride.PaymentType;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/08
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentTypeMapper {
    PaymentType toEntity(PaymentTypeRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PaymentType partialUpdate(PaymentTypeRequest paymentTypeRequest, @MappingTarget PaymentType paymentType);
}
