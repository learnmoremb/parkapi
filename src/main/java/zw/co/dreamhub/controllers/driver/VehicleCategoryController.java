package zw.co.dreamhub.controllers.driver;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.dreamhub.domain.dto.request.driver.VehicleCategoryRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.services.driver.VehicleCategoryService;
import zw.co.dreamhub.utils.ResponseUtils;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/16
 */

@RestController
@RequestMapping("${info.url.secured}/vehicle-category")
@Tag(name = "Vehicle Category Controller")
@RequiredArgsConstructor
public class VehicleCategoryController {
    private final VehicleCategoryService service;

    @PostMapping("/create")
    @Operation(summary = "Create Vehicle Category")
    public ResponseEntity<ApiResponse<String>> create(@Valid @RequestBody VehicleCategoryRequest request) {
        var response = service.create(request);
        return ResponseUtils.respond(response);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Update Vehicle Category")
    public ResponseEntity<ApiResponse<String>> update(@Valid @RequestBody VehicleCategoryRequest request,
                                                      @PathVariable String id) {
        var response = service.update(request, id);
        return ResponseUtils.respond(response);
    }
}
