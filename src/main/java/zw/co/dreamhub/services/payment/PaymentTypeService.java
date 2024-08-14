package zw.co.dreamhub.services.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.domain.dto.request.payment.PaymentTypeRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.mappers.payment.PaymentTypeMapper;
import zw.co.dreamhub.domain.models.ride.PaymentType;
import zw.co.dreamhub.domain.repositories.ride.PaymentTypeRepository;
import zw.co.dreamhub.exception.AlreadyExistException;
import zw.co.dreamhub.exception.NotFoundException;
import zw.co.dreamhub.utils.ResponseUtils;
import zw.co.dreamhub.utils.RideConstants;
import zw.co.dreamhub.utils.StringUtils;

import java.util.Optional;
import java.util.UUID;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/08
 */

@Service
@RequiredArgsConstructor
public class PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentTypeMapper mapper;
    public static final String NAME = "payment";


    // todo : add view payment types with pagination

    public ApiResponse<String> create(PaymentTypeRequest request) {
        if (paymentTypeRepository.existsByNameIgnoreCase(request.name())) {
            throw new AlreadyExistException(StringUtils.alreadyExists(request.name()));
        }
        PaymentType paymentType = mapper.toEntity(request);
        paymentTypeRepository.save(paymentType);
        return ResponseUtils.created(StringUtils.CREATED);
    }

    public ApiResponse<String> update(UUID paymentTypeId, PaymentTypeRequest request) {

        PaymentType paymentType = paymentTypeRepository.findById(String.valueOf(paymentTypeId))
                .orElseThrow(() -> new NotFoundException(StringUtils.notFound(NAME)));
        PaymentType savedPaymentType = mapper.partialUpdate(request, paymentType);
        paymentTypeRepository.save(savedPaymentType);
        return ResponseUtils.success(StringUtils.SUCCESS);
    }

    public ApiResponse<String> delete(UUID paymentTypeId) {
        PaymentType paymentType = paymentTypeRepository.findById(String.valueOf(paymentTypeId)).orElseThrow(() -> new NotFoundException(NAME));
        paymentTypeRepository.delete(paymentType);
        return ResponseUtils.success(StringUtils.SUCCESS);
    }

}
