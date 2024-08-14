package zw.co.dreamhub.services.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.domain.dto.request.driver.DriversLicenseRequest;
import zw.co.dreamhub.domain.dto.request.driver.ImageRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.mappers.driver.DriversLicenseMapper;
import zw.co.dreamhub.domain.models.driver.DriversLicense;
import zw.co.dreamhub.domain.projections.driver.DriversLicenseInfo;
import zw.co.dreamhub.domain.repositories.driver.DriversLicenseRepository;
import zw.co.dreamhub.exception.AlreadyExistException;
import zw.co.dreamhub.exception.NotFoundException;
import zw.co.dreamhub.services.MediaService;
import zw.co.dreamhub.utils.ResponseUtils;
import zw.co.dreamhub.utils.StringUtils;

import java.util.Optional;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/15
 */

@Service
@RequiredArgsConstructor
public class DriversLicenseService {
    private final DriversLicenseRepository driversLicenseRepository;
    private final DriversLicenseMapper driversLicenseMapper;
    private final DriversLicenseOperations driversLicenseOperations;
    private final MediaService mediaService;

    private static final String NAME = "license";

    public ApiResponse<String> create(DriversLicenseRequest request) {
        if (driversLicenseRepository.existsDriversLicenseByLicenseNumber(request.licenseNumber())) {
            throw new AlreadyExistException(StringUtils.alreadyExists(request.licenseNumber()));
        }
        DriversLicense driversLicense = driversLicenseMapper.toEntity(request);
        driversLicenseRepository.save(driversLicense);
        return ResponseUtils.success(StringUtils.SUCCESS);
    }

    public ApiResponse<DriversLicenseInfo> getByLicenseNumber(String licenseNumber) {
        Optional<DriversLicenseInfo> data = driversLicenseRepository.findDriversLicenseByLicenseNumber(licenseNumber);
        return data.map(ResponseUtils::success).orElseThrow(() -> new NotFoundException(NAME));
    }

    public ApiResponse<String> uploadDriverLicenseImages(String driversLicenseId, ImageRequest imageRequest) {
        Optional<DriversLicense> vehicle = driversLicenseRepository.findById(driversLicenseId);
        return vehicle.map(v -> mediaService
                        .uploadImages(imageRequest.images(), v, driversLicenseRepository, driversLicenseOperations))
                .orElseThrow(() -> new NotFoundException(NAME));
    }
}
