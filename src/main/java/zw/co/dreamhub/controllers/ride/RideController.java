package zw.co.dreamhub.controllers.ride;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.dreamhub.domain.dto.request.ride.RideAcceptanceRequest;
import zw.co.dreamhub.domain.dto.request.ride.RideRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.models.enums.PaymentStatus;
import zw.co.dreamhub.services.ride.RideService;
import zw.co.dreamhub.utils.ResponseUtils;

import java.util.UUID;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/08
 */

@RestController
@RequestMapping("${info.url.unSecured}/ride")
@Tag(name = "Ride Controller")
@RequiredArgsConstructor
public class RideController {

    private final RideService service;
    @PutMapping(value = "/start/{rideId}")
    @Operation(summary = "Booking Ride")
    public ResponseEntity<ApiResponse<String>> startRide(@PathVariable UUID rideId) {
        var response = service.startRide(rideId);
        return ResponseUtils.respond(response);
    }

    @PostMapping(value = "/request")
    @Operation(summary = "Request Ride")
    public ResponseEntity<ApiResponse<String>> requestForRide(@Valid @RequestBody RideRequest request) {
        var response = service.requestForRide(request);
        return ResponseUtils.respond(response);
    }

    @PatchMapping(value = "/cancel/{rideId}")
    @Operation(summary = "Cancel Ride Request")
    public ResponseEntity<ApiResponse<String>> cancelRideRequest(@PathVariable UUID rideId) {
        var response = service.cancelRideRequest(rideId);
        return ResponseUtils.respond(response);
    }

    @PutMapping(value = "/accept")
    @Operation(summary = "Accept Ride Request")
    public ResponseEntity<ApiResponse<String>> acceptRideRequest(@Valid @RequestBody RideAcceptanceRequest request) {
        var response = service.acceptRideRequest(request);
        return ResponseUtils.respond(response);
    }

    @PatchMapping(value = "/end")
    @Operation(summary = "End Ride")
    public ResponseEntity<ApiResponse<String>> endRide(@RequestParam UUID rideId) {
        var response = service.endRide(rideId);
        return ResponseUtils.respond(response);
    }

    @PutMapping(value = "/change-payment")
    @Operation(summary = "Change Ride Payment")
    public ResponseEntity<ApiResponse<String>> changeRidePayment(@RequestParam UUID rideId, @Valid @RequestBody RideRequest.PaymentDetailsRequest request) {
        var response = service.changeRidePayment(rideId,request);
        return ResponseUtils.respond(response);
    }

    @GetMapping(value = "/payment-status")
    @Operation(summary = "Check Ride Payment Status")
    public ResponseEntity<ApiResponse<PaymentStatus>> checkRidePaymentStatus(@RequestParam UUID rideId) {
        var response = service.checkForRidePaymentStatus(rideId);
        return ResponseUtils.respond(response);
    }


}
