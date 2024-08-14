package zw.co.dreamhub.controllers.passenger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.dreamhub.domain.dto.request.passenger.PassengerRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.services.passenger.PassengerService;
import zw.co.dreamhub.utils.ResponseUtils;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/20
 */
@RestController
@RequestMapping("${info.url.unSecured}/passenger")
@Tag(name = "Passenger Controller")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    @PutMapping(value = "/update")
    @Operation(summary = "Update Passenger")
    public ResponseEntity<ApiResponse<String>> update(@Valid @RequestBody PassengerRequest request) {
        var response = passengerService.update(request);
        return ResponseUtils.respond(response);
    }
}
