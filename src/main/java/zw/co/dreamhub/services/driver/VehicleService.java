package zw.co.dreamhub.services.driver;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.domain.dto.request.driver.ImageRequest;
import zw.co.dreamhub.domain.dto.request.driver.VehicleRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.dto.response.PaginatedResponse;
import zw.co.dreamhub.domain.mappers.driver.VehicleMapper;
import zw.co.dreamhub.domain.models.driver.Vehicle;
import zw.co.dreamhub.domain.models.driver.VehicleCategory;
import zw.co.dreamhub.domain.projections.driver.VehicleInfo;
import zw.co.dreamhub.domain.repositories.driver.VehicleCategoryRepository;
import zw.co.dreamhub.domain.repositories.driver.VehicleRepository;
import zw.co.dreamhub.exception.NotFoundException;
import zw.co.dreamhub.services.MediaService;
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
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleCategoryRepository vehicleCategoryRepository;
    private final VehicleMapper vehicleMapper;
    private final VehicleImageOperations vehicleImageOperations;
    private final MediaService mediaService;

    private static final String NAME = "Vehicle";

    public ApiResponse<String> registerVehicle(VehicleRequest vehicleRequest) {
        if (vehicleRepository.existsByRegistrationNumberIgnoreCase(vehicleRequest.registration())) {
            return ResponseUtils.badRequest(StringUtils.alreadyExists(vehicleRequest.registration()));
        }
        VehicleCategory vehicleCategory = vehicleCategoryRepository.findVehicleCategoryByNameIgnoreCase(vehicleRequest.categoryName())
                .orElseThrow(() -> new NotFoundException(StringUtils.notFound(vehicleRequest.categoryName())));

        Vehicle vehicle = vehicleMapper.toEntity(vehicleRequest);
        vehicle.setCategory(vehicleCategory);
        vehicleRepository.save(vehicle);
        return ResponseUtils.created(StringUtils.CREATED);
    }

    public ApiResponse<PaginatedResponse<VehicleInfo>> getAll(
            int pageNumber,
            int pageCount) {
        Page<VehicleInfo> vehicle = vehicleRepository.findAllByOrderByRegistrationNumber(PageRequest.of(pageNumber, pageCount));
        return ResponseUtils.success(
                ResponseUtils.pageResponse(vehicle, pageNumber)
        );
    }

    @Transactional
    public ApiResponse<VehicleInfo> getByRegistrationNumber(String registrationNumber) {
        Optional<VehicleInfo> vehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);
        return vehicle.map(ResponseUtils::success).orElseThrow(() -> new NotFoundException(NAME));
    }


    public ApiResponse<String> update(VehicleRequest request, String id) {
        if (vehicleRepository.existsByRegistrationNumberIgnoreCase(request.registration())) {
            return ResponseUtils.badRequest(StringUtils.alreadyExists(request.registration()));
        }
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(StringUtils.notFound(id)));
        vehicleMapper.partialUpdate(request, vehicle);
        vehicleRepository.save(vehicle);
        return ResponseUtils.success(StringUtils.SUCCESS);

    }

    public ApiResponse<VehicleInfo> getById(String id) {
        Optional<VehicleInfo> vehicle = vehicleRepository.findVehicleById(id);
        return vehicle.map(ResponseUtils::success).orElseThrow(() -> new NotFoundException(NAME));
    }


    public ApiResponse<String> uploadVehicleImages(String vehicleId, ImageRequest request) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        return vehicle.map(v -> mediaService.uploadImages(request.images(), v, vehicleRepository, vehicleImageOperations))
                .orElseThrow(() -> new NotFoundException(NAME));
    }
}
