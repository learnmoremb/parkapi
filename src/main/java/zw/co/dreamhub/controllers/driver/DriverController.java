package zw.co.dreamhub.controllers.driver;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.dreamhub.domain.dto.request.common.EmergencyContactRequest;
import zw.co.dreamhub.domain.dto.request.driver.DriverRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.security.services.AuthUtils;
import zw.co.dreamhub.services.common.EmergencyContactService;
import zw.co.dreamhub.services.driver.DriverService;
import zw.co.dreamhub.utils.ResponseUtils;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/17
 */

@RestController
@RequestMapping("${info.url.unSecured}/drivers")
@Tag(name = "Driver Controller")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @PutMapping(value = "/update")
    @Operation(summary = "Update Driver")
    public ResponseEntity<ApiResponse<String>> update(@Valid @RequestBody DriverRequest request) {
        var response = driverService.update(request);
        return ResponseUtils.respond(response);
    }


    // todo : add update for user details


}
