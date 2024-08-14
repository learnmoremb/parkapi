package zw.co.dreamhub.controllers.payment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.dreamhub.domain.dto.request.payment.PaymentTypeRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.services.payment.PaymentTypeService;
import zw.co.dreamhub.utils.ResponseUtils;

import java.util.UUID;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/08
 */
@RestController
@RequestMapping("${info.url.unSecured}/payment-type")
@Tag(name = "Payment Type Controller")
@RequiredArgsConstructor
public class PaymentTypeController {

    private final PaymentTypeService service;
    @PostMapping(value = "/create")
    @Operation(summary = "Create Payment Type")
    public ResponseEntity<ApiResponse<String>> create(@Valid @RequestBody PaymentTypeRequest request) {
        var response = service.create(request);
        return ResponseUtils.respond(response);
    }

    @PutMapping(value = "/update/{paymentTypeId}")
    @Operation(summary = "Update Payment Type")
    public ResponseEntity<ApiResponse<String>> update(@PathVariable UUID paymentTypeId,
                                                      @Valid @RequestBody PaymentTypeRequest request) {
        var response = service.update(paymentTypeId,request);
        return ResponseUtils.respond(response);
    }

    @DeleteMapping(value = "/delete")
    @Operation(summary = "Delete Payment Type")
    public ResponseEntity<ApiResponse<String>> delete(@RequestParam UUID paymentTypeId) {
        var response = service.delete(paymentTypeId);
        return ResponseUtils.respond(response);
    }


}
