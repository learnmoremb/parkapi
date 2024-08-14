package zw.co.dreamhub.controllers.driver;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.dreamhub.domain.dto.request.driver.InsuranceRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.projections.driver.InsuranceInfo;
import zw.co.dreamhub.services.driver.InsuranceService;
import zw.co.dreamhub.utils.ResponseUtils;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/14
 */

@RestController
@RequestMapping("${info.url.secured}/insurance")
@Tag(name = "Insurance Controller")
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranceService service;

    @PostMapping(value = "/create")
    @Operation(summary = "Create Insurance")
    public ResponseEntity<ApiResponse<String>> create(@Valid @RequestBody InsuranceRequest request) {
        var response = service.create(request);
        return ResponseUtils.respond(response);
    }

    @PutMapping(value = "/update/{insuranceId}")
    @Operation(summary = "Update Insurance")
    public ResponseEntity<ApiResponse<String>> update(@PathVariable String insuranceId,
                                                      @Valid @RequestBody InsuranceRequest request) {
        var response = service.update(insuranceId,request);
        return ResponseUtils.respond(response);
    }

    @GetMapping
    @Operation(summary = "Get Insurance By Vehicle Id")
    public ResponseEntity<ApiResponse<InsuranceInfo>> getInsuranceByVehicleId(@RequestParam String vehicleId) {
        var response = service.getInsuranceByVehicleId(vehicleId);
        return ResponseUtils.respond(response);
    }

}
