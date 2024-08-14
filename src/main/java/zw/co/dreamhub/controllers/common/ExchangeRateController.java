package zw.co.dreamhub.controllers.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.dreamhub.domain.dto.request.common.ExchangeRateRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.services.common.ExchangeRateService;
import zw.co.dreamhub.utils.ResponseUtils;

import java.util.UUID;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/18
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${info.url.secured}/exchange-rate")
@Tag(name = "Exchange - Rate Controller")
public class ExchangeRateController {
    private final ExchangeRateService service;

    @PostMapping("/create")
    @Operation(summary = "Create Exchange Rate")
    public ResponseEntity<ApiResponse<String>> create(@Valid @RequestBody ExchangeRateRequest request) {
        var response = service.create(request);
        return ResponseUtils.respond(response);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update Exchange Rate")
    public ResponseEntity<ApiResponse<String>> update(@PathVariable UUID id,
                                                      @Valid @RequestBody ExchangeRateRequest request) {
        var response = service.update(id, request);
        return ResponseUtils.respond(response);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Update Exchange Rate")
    public ResponseEntity<ApiResponse<String>> delete(@RequestParam UUID id) {
        var response = service.delete(id);
        return ResponseUtils.respond(response);
    }
}
