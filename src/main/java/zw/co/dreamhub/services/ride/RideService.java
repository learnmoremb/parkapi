package zw.co.dreamhub.services.ride;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.domain.dto.request.paynow.PayNowRequest;
import zw.co.dreamhub.domain.dto.request.paynow.PayNowWebHookRequest;
import zw.co.dreamhub.domain.dto.request.ride.RideAcceptanceRequest;
import zw.co.dreamhub.domain.dto.request.ride.RideRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.dto.response.paynow.PayNowResponse;
import zw.co.dreamhub.domain.mappers.common.AddressMapper;
import zw.co.dreamhub.domain.mappers.common.AmountMapper;
import zw.co.dreamhub.domain.models.common.ExchangeRate;
import zw.co.dreamhub.domain.models.common.Payment;
import zw.co.dreamhub.domain.models.driver.Driver;
import zw.co.dreamhub.domain.models.driver.Vehicle;
import zw.co.dreamhub.domain.models.enums.Currency;
import zw.co.dreamhub.domain.models.enums.PaymentStatus;
import zw.co.dreamhub.domain.models.enums.RideStatus;
import zw.co.dreamhub.domain.models.passenger.Passenger;
import zw.co.dreamhub.domain.models.ride.Ride;
import zw.co.dreamhub.domain.models.ride.RideType;
import zw.co.dreamhub.domain.repositories.common.ExchangeRateRepository;
import zw.co.dreamhub.domain.repositories.common.PaymentRepository;
import zw.co.dreamhub.domain.repositories.driver.DriverRepository;
import zw.co.dreamhub.domain.repositories.passenger.PassengerRepository;
import zw.co.dreamhub.domain.repositories.ride.RideRepository;
import zw.co.dreamhub.exception.NotFoundException;
import zw.co.dreamhub.exception.ValidationException;
import zw.co.dreamhub.services.paynow.PayNowService;
import zw.co.dreamhub.utils.ResponseUtils;
import zw.co.dreamhub.utils.RideConstants;
import zw.co.dreamhub.utils.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/21
 */

@Service
@RequiredArgsConstructor
public class RideService {

    private final RideRepository rideRepository;
    private final DriverRepository driverRepository;
    private final PaymentRepository paymentRepository;
    private final PassengerRepository passengerRepository;
    private final ExchangeRateRepository exchangeRateRepository;

    private final AddressMapper addressMapper;
    private final AmountMapper amountMapper;

    private final RideUtils rideUtils;
    private final PayNowService payNowService;

    public ApiResponse<String> startRide(UUID rideId) {

        Ride ride = rideUtils.findRideById(rideId.toString());
        rideUtils.updateRide(
                ride,
                RideStatus.EN_ROUTE,
                RideConstants.RIDE_START_BODY,
                RideConstants.RIDE_STATUS_TITLE
        );

        return ResponseUtils.success(StringUtils.SUCCESS);
    }

    // todo : view rides by passenger / driver -> in controller & service
    // todo : panic button for ride  in controller & service
    // todo : location details ie lat & lon for driver & passenger ->  in controller & service

    public ApiResponse<String> endRide(UUID rideId) {
        Ride ride = rideUtils.findRideById(rideId.toString());
        rideUtils.updateRide(
                ride,
                RideStatus.ARRIVED,
                RideConstants.RIDE_END_BODY,
                RideConstants.RIDE_STATUS_TITLE
        );

        return ResponseUtils.success(StringUtils.SUCCESS);
    }

    public ApiResponse<String> cancelRideRequest(UUID rideId) {

        Ride ride = rideUtils.findRideById(String.valueOf(rideId));
        rideUtils.updateRide(
                ride,
                RideStatus.CANCELLED,
                RideConstants.RIDE_REQUEST_CANCELLATION_BODY,
                RideConstants.RIDE_REQUEST_CANCELLATION_BODY
        );

        return ResponseUtils.success(StringUtils.SUCCESS);
    }

    public ApiResponse<String> requestForRide(RideRequest request) {

        Set<Passenger> passengers = passengerRepository.findByUser_IdIn(
                request.details().passengers().stream().map(UUID::toString).collect(Collectors.toSet())
        );

        if (passengers.size() == request.details().passengers().size()) {
            RideType rideType = rideUtils.findRideType(String.valueOf(request.rideTypeId()));
            Set<Driver> drivers = rideUtils.findAvailableDrivers(request.details().country());
            if (!drivers.isEmpty()) {
                Ride ride = new Ride(
                        rideType, passengers,
                        addressMapper.toEntity(request.details().from()),
                        addressMapper.toEntity(request.details().to()),
                        new Payment(
                                amountMapper.toEntity(request.payment().amount()),
                                request.payment().type(),
                                request.payment().account(),
                                request.payment().email()
                        )
                );
                rideRepository.save(ride);

                rideUtils.broadcastFirebaseNotification(
                        StringUtils.commaSeparateCollection(
                                drivers.stream().map(d -> d.getUser()
                                                .getDeviceTokens())
                                        .flatMap(Collection::stream)
                                        .collect(Collectors.toSet())
                        ),
                        rideUtils.rideRequestMessage(ride),
                        RideConstants.RIDE_REQUEST_TITLE
                );

                return ResponseUtils.created(StringUtils.SUCCESS);
            } else {
                throw new NotFoundException("Driver(s) not found");
            }
        } else {
            throw new NotFoundException("Passenger(s) not found");
        }


    }

    public ApiResponse<String> acceptRideRequest(RideAcceptanceRequest request) {

        Driver driver = driverRepository.findById(request.driverId().toString())
                .orElseThrow(() -> new NotFoundException(StringUtils.notFound(RideConstants.DRIVER)));
        Vehicle vehicle = driver
                .getVehicles()
                .stream()
                .filter(v -> v.getId().equals(request.vehicleId().toString()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Vehicle"));

        Ride ride = rideUtils.findRideById(request.rideId().toString());

        if (ride.getStatus().equals(RideStatus.REQUESTED)) {
            ride.acceptRide(driver, vehicle, RideStatus.ACCEPTED);
            rideRepository.save(ride);

            rideUtils.broadcastFirebaseNotification(
                    StringUtils.commaSeparateCollection(
                            StringUtils.getPassengerNotificationTokens(ride.getPassengers())
                    ),
                    RideConstants.SUCCESSFUL_RIDE_ACCEPTANCE_BODY,
                    RideConstants.RIDE_ACCEPTANCE_TITLE
            );

            return ResponseUtils.success(StringUtils.SUCCESS);
        } else {

            rideUtils.broadcastFirebaseNotification(
                    StringUtils.commaSeparateCollection(
                            ride.getDriver().getUser().getDeviceTokens()
                    ),
                    RideConstants.ERROR_RIDE_ACCEPTANCE_BODY,
                    RideConstants.RIDE_ACCEPTANCE_TITLE
            );

            return ResponseUtils.badRequest(RideConstants.ERROR_RIDE_ACCEPTANCE_BODY);
        }

    }

    public ApiResponse<String> changeRidePayment(UUID rideId,
                                                 RideRequest.PaymentDetailsRequest request) {

        Ride ride = rideUtils.findRideById(rideId.toString());

        ride.getPayment().updatePayment(
                amountMapper.partialUpdate(request.amount(), ride.getPayment().getAmount()),
                request.type(), request.account(), request.email()

        );
        paymentRepository.save(ride.getPayment());

        rideUtils.broadcastFirebaseNotification(
                StringUtils.commaSeparateCollection(
                        rideUtils.getRideNotificationTokens(ride)
                ),
                rideUtils.rideRequestAmountMessage(ride),
                RideConstants.RIDE_STATUS_TITLE
        );

        return ResponseUtils.success(StringUtils.SUCCESS);
    }

    // todo : add in controller
    public ApiResponse<String> processRidePayment(UUID rideId) {
        Ride ride = rideUtils.findRideById(rideId.toString());

        ApiResponse<String> response;
        String paymentType = ride.getPayment().getType().getName();

        if (
                Stream.of("ECOCASH", "ONE_MONEY", "INN_BUCKS")
                        .anyMatch(e -> e.equalsIgnoreCase(paymentType))
        ) {
            BigDecimal amount = ride.getPayment().getAmount().getValue();
            if (ride.getPayment().getAmount().getCurrency().equals(Currency.ZWL)) {
                ExchangeRate rate = exchangeRateRepository
                        .findFirstByAmount_CurrencyOrderByDateCreatedDesc(Currency.ZWL)
                        .orElseThrow(() -> new NotFoundException(StringUtils.notFound("Exchange Rate")));
                amount = BigDecimal.valueOf(
                        amount.doubleValue() / rate.getAmount().getValue().doubleValue()
                );
            }

            PayNowResponse paymentResponse = payNowService.mobile(new PayNowRequest(
                    amount.doubleValue(),
                    ride.getPayment().getId(),
                    rideUtils.getMobileMoneyType(ride.getPayment().getType()),
                    ride.getPayment().getAccount(),
                    ride.getPayment().getEmail()
            ));

            if (paymentResponse.status().equals(PayNowResponse.TransactionStatus.SUCCESS)) {
                ride.getPayment().setUpstreamStatusResponse(paymentResponse.statusUrl());
                ride.getPayment().setStatus(PaymentStatus.INITIATED);
                paymentRepository.save(ride.getPayment());

                rideUtils.broadcastFirebaseNotification(
                        StringUtils.commaSeparateCollection(
                                rideUtils.getRideNotificationTokens(ride)
                        ),
                        RideConstants.RIDE_PAYMENT_INITIATED,
                        RideConstants.RIDE_PAYMENT_TITLE
                );

                response = ResponseUtils.created(StringUtils.CREATED);
            } else {
                ride.getPayment().setStatus(PaymentStatus.FAILED);
                ride.getPayment().setDescription("Failed to initialize payment");
                paymentRepository.save(ride.getPayment());

                rideUtils.broadcastFirebaseNotification(
                        StringUtils.commaSeparateCollection(
                                rideUtils.getRideNotificationTokens(ride)
                        ),
                        RideConstants.RIDE_PAYMENT_FAILED,
                        RideConstants.RIDE_PAYMENT_TITLE
                );

                response = ResponseUtils.badRequest("Payment failed");
            }
        }
        // cash
        else {
            ride.getPayment().setStatus(PaymentStatus.SUCCESS);
            ride.getPayment().setDescription(PaymentStatus.SUCCESS.name());
            paymentRepository.save(ride.getPayment());
            // todo : notify driver to collect cash
            response = ResponseUtils.created(StringUtils.CREATED);
        }

        return response;
    }

    public ApiResponse<PaymentStatus> checkForRidePaymentStatus(UUID rideId) {
        Ride ride = rideUtils.findRideById(rideId.toString());
        return ResponseUtils.success(ride.getPayment().getStatus());
    }

    public ApiResponse<String> processPayNowPayment(PayNowWebHookRequest request) {

        Ride ride = rideRepository
                .findByPayment_Id(request.id())
                .orElseThrow(() -> new NotFoundException(StringUtils.notFound("payment")));
        if (ride.getPayment().getStatus().equals(PaymentStatus.SUCCESS)) {
            throw new ValidationException("Payment already processed");
        } else {
            ride.getPayment().setStatus(
                    request.status().equalsIgnoreCase("Paid") ? PaymentStatus.SUCCESS : PaymentStatus.FAILED
            );
            paymentRepository.save(ride.getPayment());

            rideUtils.broadcastFirebaseNotification(
                    StringUtils.commaSeparateCollection(
                            rideUtils.getRideNotificationTokens(ride)
                    ),
                    ride.getPayment().getStatus().equals(PaymentStatus.SUCCESS) ?
                            RideConstants.RIDE_PAYMENT_SUCCESS : RideConstants.RIDE_PAYMENT_FAILED,
                    RideConstants.RIDE_PAYMENT_TITLE
            );

            return ride.getPayment().getStatus().equals(PaymentStatus.SUCCESS) ?
                    ResponseUtils.success(StringUtils.SUCCESS) : ResponseUtils.badRequest("Payment failed");
        }

    }

}
