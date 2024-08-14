package zw.co.dreamhub.services.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.domain.dto.request.driver.InsuranceRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.mappers.driver.InsuranceMapper;
import zw.co.dreamhub.domain.models.driver.Insurance;
import zw.co.dreamhub.domain.models.driver.Vehicle;
import zw.co.dreamhub.domain.projections.driver.InsuranceInfo;
import zw.co.dreamhub.domain.projections.driver.VehicleInfo;
import zw.co.dreamhub.domain.repositories.driver.InsuranceRepository;
import zw.co.dreamhub.domain.repositories.driver.VehicleRepository;
import zw.co.dreamhub.exception.NotFoundException;
import zw.co.dreamhub.utils.ResponseUtils;
import zw.co.dreamhub.utils.StringUtils;

import java.util.Optional;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/14
 */
@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final InsuranceMapper insuranceMapper;
    private final VehicleRepository vehicleRepository;
    private static final String NAME = "Insurance";

    public ApiResponse<String> create(InsuranceRequest insuranceRequest) {
        Vehicle vehicle = vehicleRepository.findById(insuranceRequest.vehicleId())
                .orElseThrow(()-> new NotFoundException(StringUtils.notFound(insuranceRequest.vehicleId())));
        Insurance insurance = insuranceMapper.toEntity(insuranceRequest);
        insurance.setVehicle(vehicle);
        insuranceRepository.save(insurance);
        return ResponseUtils.created(StringUtils.CREATED);
    }

    public ApiResponse<String> update(String insuranceId, InsuranceRequest insuranceRequest){
        Insurance insurance = insuranceRepository.findById(insuranceId)
                .orElseThrow(()-> new NotFoundException(StringUtils.notFound(NAME)));
        Vehicle vehicle = vehicleRepository.findById(insuranceRequest.vehicleId())
                .orElseThrow(()-> new NotFoundException(StringUtils.notFound(insuranceRequest.vehicleId())));
        Insurance savedInsurance = insuranceMapper.partialUpdate(insuranceRequest,insurance);
        savedInsurance.setVehicle(vehicle);
        insuranceRepository.save(savedInsurance);
        return ResponseUtils.created(StringUtils.SUCCESS);
    }

    public ApiResponse<InsuranceInfo> getInsuranceByVehicleId(String vehicleId){
            Optional<InsuranceInfo> insurance = insuranceRepository.findInsuranceByVehicle_Id(vehicleId);
            return insurance.map(ResponseUtils::success).orElseThrow(() -> new NotFoundException(NAME));

    }
}
