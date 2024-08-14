package zw.co.dreamhub.services.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.domain.dto.request.driver.VehicleCategoryRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.mappers.driver.VehicleCategoryMapper;
import zw.co.dreamhub.domain.models.driver.VehicleCategory;
import zw.co.dreamhub.domain.repositories.driver.VehicleCategoryRepository;
import zw.co.dreamhub.exception.AlreadyExistException;
import zw.co.dreamhub.exception.NotFoundException;
import zw.co.dreamhub.utils.ResponseUtils;
import zw.co.dreamhub.utils.StringUtils;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/16
 */

@Service
@RequiredArgsConstructor
public class VehicleCategoryService {

    private final VehicleCategoryRepository vehicleCategoryRepository;
    private final VehicleCategoryMapper vehicleCategoryMapper;

    public ApiResponse<String> create(VehicleCategoryRequest request) {
        if (vehicleCategoryRepository.existsVehicleCategoryByNameIgnoreCase(request.name())) {
            throw new AlreadyExistException(StringUtils.alreadyExists(request.name()));
        } else {
            VehicleCategory vehicleCategory = vehicleCategoryMapper.toEntity(request);
            vehicleCategoryRepository.save(vehicleCategory);
        }
        return ResponseUtils.created(StringUtils.CREATED);
    }

    public ApiResponse<String> update(VehicleCategoryRequest request, String id) {
        VehicleCategory vehicleCategory = vehicleCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(StringUtils.notFound(id)));
        VehicleCategory vc = vehicleCategoryMapper.partialUpdate(request, vehicleCategory);
        vehicleCategoryRepository.save(vc);
        return ResponseUtils.created(StringUtils.CREATED);

    }
}
