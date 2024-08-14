package zw.co.dreamhub.services.driver;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.domain.dto.request.driver.DriverRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.mappers.driver.DriverMapper;
import zw.co.dreamhub.domain.models.driver.Driver;
import zw.co.dreamhub.domain.repositories.common.AddressRepository;
import zw.co.dreamhub.domain.repositories.driver.DriverRepository;
import zw.co.dreamhub.exception.NotFoundException;
import zw.co.dreamhub.utils.ResponseUtils;
import zw.co.dreamhub.utils.StringUtils;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/17
 */

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;
    private final AddressRepository addressRepository;
    private final DriverMapper mapper;
    private final static String NAME = "user";


    @Transactional
    public ApiResponse<String> update(DriverRequest request) {
        Driver driver = driverRepository.findDriverByUser_Id(request.userId())
                .orElseThrow(() -> new NotFoundException(StringUtils.notFound(NAME)));
        Driver updatedDriver = mapper.partialUpdate(request, driver);
        addressRepository.save(updatedDriver.getAddress());
        driverRepository.save(updatedDriver);
        return ResponseUtils.success(StringUtils.SUCCESS);
    }


}
