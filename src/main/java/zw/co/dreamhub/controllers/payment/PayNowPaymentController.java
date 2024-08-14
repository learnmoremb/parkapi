package zw.co.dreamhub.controllers.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.dreamhub.domain.dto.request.paynow.PayNowWebHookRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.services.ride.RideService;
import zw.co.dreamhub.utils.ResponseUtils;
import zw.co.dreamhub.utils.StringUtils;

import java.net.URL;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 17/1/2024
 */

@RestController
@RequestMapping("${info.url.unSecured}/ride/payment")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Payments Notification Controller", description = "payments notification controller")
public class PayNowPaymentController {

    private final RideService service;
    private final ObjectMapper mapper;
    private final HttpServletRequest httpServletRequest;

    @PostMapping(value = "/paynow", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "PayNow Payment Notification", description = "PayNow Payment Notification")
    public ResponseEntity<ApiResponse<String>> payNowPaymentResponse(@ModelAttribute PayNowWebHookRequest request) {

        // todo : allow requests from allowed paynow origins only

        try {
            log.info("Webhook Host : {}", new URL(httpServletRequest.getRequestURL().toString()).getHost());

            var response = service.processPayNowPayment(request);

            log.info("Webhook Request : {}", mapper.writeValueAsString(request));
            log.info("Webhook Response : {}", mapper.writeValueAsString(response));

        } catch (Exception e) {
            log.info("Exception on PayNow webhook : {}", e.getMessage());
        }

        return ResponseUtils.respond(ResponseUtils.success(StringUtils.SUCCESS));
    }
}
