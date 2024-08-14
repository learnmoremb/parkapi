package zw.co.dreamhub.controllers.driver;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.dreamhub.domain.dto.request.driver.ImageRequest;
import zw.co.dreamhub.domain.dto.request.driver.VehicleRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.dto.response.PaginatedResponse;
import zw.co.dreamhub.domain.projections.driver.VehicleInfo;
import zw.co.dreamhub.services.driver.VehicleService;
import zw.co.dreamhub.utils.ResponseUtils;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/14
 */

@RestController
@RequestMapping("${info.url.unSecured}/vehicles")
@Tag(name = "Vehicle Controller")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService service;


    @PostMapping(value = "/register")
    @Operation(summary = "Register Vehicle")
    public ResponseEntity<ApiResponse<String>> registerVehicle(@Valid @RequestBody VehicleRequest request) {
        var response = service.registerVehicle(request);
        return ResponseUtils.respond(response);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Update Vehicle")
    public ResponseEntity<ApiResponse<String>> update(@Valid @RequestBody VehicleRequest request,
                                                      @PathVariable String id) {
        var response = service.update(request, id);
        return ResponseUtils.respond(response);
    }

    @GetMapping(value = "/{registrationNumber}")
    @Operation(summary = "Get Vehicle By Registration Number")
    public ResponseEntity<ApiResponse<VehicleInfo>> getByRegistrationNumber(@PathVariable String registrationNumber) {
        var response = service.getByRegistrationNumber(registrationNumber);
        return ResponseUtils.respond(response);
    }

    @GetMapping
    @Operation(summary = "Get All Vehicles", description = "Get All Vehicles")
    public ResponseEntity<ApiResponse<PaginatedResponse<VehicleInfo>>> getAllVehicles(
            @RequestParam(name = "pageNumber") int pageNumber,
            @RequestParam(name = "pageCount") int pageCount
    ) {
        var response = service.getAll(pageNumber, pageCount);
        return ResponseUtils.respond(response);
    }

    @GetMapping(value = "/id")
    @Operation(summary = "Get Vehicle By ID")
    public ResponseEntity<ApiResponse<VehicleInfo>> getById(@RequestParam String id) {
        var response = service.getById(id);
        return ResponseUtils.respond(response);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Upload Vehicle Images", description = "Upload Vehicle Images")
    public ResponseEntity<ApiResponse<String>> uploadVehicleImages(
            @RequestParam(name = "vehicleId") String vehicleId,
            @ModelAttribute ImageRequest request
    ) {
        var response = service.uploadVehicleImages(vehicleId, request);
        return ResponseUtils.respond(response);
    }


}
