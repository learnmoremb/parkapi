package zw.co.dreamhub.controllers.driver;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.dreamhub.domain.dto.request.driver.DriversLicenseRequest;
import zw.co.dreamhub.domain.dto.request.driver.ImageRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.projections.driver.DriversLicenseInfo;
import zw.co.dreamhub.services.driver.DriversLicenseService;
import zw.co.dreamhub.utils.ResponseUtils;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/15
 */
@RestController
@RequestMapping("${info.url.secured}/drivers-license")
@Tag(name = "Driver's - License Controller")
@RequiredArgsConstructor
public class DriversLicenseController {

    private final DriversLicenseService service;

    @PostMapping(value = "/create")
    @Operation(summary = "Create Driver's License")
    public ResponseEntity<ApiResponse<String>> create(@Valid @RequestBody DriversLicenseRequest request) {
        var response = service.create(request);
        return ResponseUtils.respond(response);
    }

    @GetMapping(value = "/{licenseNumber}")
    @Operation(summary = "Get License By License Number")
    public ResponseEntity<ApiResponse<DriversLicenseInfo>> getByLicenseNumber(@PathVariable @NotNull @NotEmpty String licenseNumber) {
        var response = service.getByLicenseNumber(licenseNumber);
        return ResponseUtils.respond(response);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Upload Drivers License Images", description = "Upload Drivers License Images")
    public ResponseEntity<ApiResponse<String>> uploadDriversLicenseImages(
            @RequestParam(name = "driversLicenseId") @NotNull @NotEmpty String driversLicenseId,
            @ModelAttribute ImageRequest request
    ) {
        var response = service.uploadDriverLicenseImages(driversLicenseId, request);
        return ResponseUtils.respond(response);
    }
}
