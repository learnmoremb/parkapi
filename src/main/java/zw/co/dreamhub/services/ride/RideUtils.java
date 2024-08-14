package zw.co.dreamhub.services.ride;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.domain.dto.request.MessengerRequest;
import zw.co.dreamhub.domain.dto.request.paynow.PayNowRequest;
import zw.co.dreamhub.domain.models.driver.Driver;
import zw.co.dreamhub.domain.models.enums.MessageType;
import zw.co.dreamhub.domain.models.enums.RideStatus;
import zw.co.dreamhub.domain.models.enums.Status;
import zw.co.dreamhub.domain.models.ride.PaymentType;
import zw.co.dreamhub.domain.models.ride.Ride;
import zw.co.dreamhub.domain.models.ride.RideType;
import zw.co.dreamhub.domain.repositories.driver.DriverRepository;
import zw.co.dreamhub.domain.repositories.ride.RideRepository;
import zw.co.dreamhub.domain.repositories.ride.RideTypeRepository;
import zw.co.dreamhub.exception.NotFoundException;
import zw.co.dreamhub.services.MessengerService;
import zw.co.dreamhub.utils.RideConstants;
import zw.co.dreamhub.utils.StringUtils;

import java.util.Set;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 8/1/2024
 */


@Service
@RequiredArgsConstructor
public class RideUtils {

    private final RideTypeRepository rideTypeRepository;
    private final DriverRepository driverRepository;
    private final RideRepository rideRepository;

    private final MessengerService messengerService;

    public PayNowRequest.MobileMoneyType getMobileMoneyType(PaymentType paymentType) {
        String name = paymentType.getName().toLowerCase();
        if (name.equalsIgnoreCase("ECOCASH")) {
            return PayNowRequest.MobileMoneyType.PAYNOW_ECOCASH;
        } else if (name.equalsIgnoreCase("ONE_MONEY")) {
            return PayNowRequest.MobileMoneyType.PAYNOW_ONE_MONEY;
        } else {
            return PayNowRequest.MobileMoneyType.PAYNOW_ECOCASH;
        }
    }

    public RideType findRideType(String rideId) {
        return rideTypeRepository.findById(rideId)
                .orElseThrow(() -> new NotFoundException(RideConstants.RIDE_TYPE));
    }

    public String rideRequestMessage(Ride ride) {
        return String.format("New ride request from %s to %s", ride.getPickUpAddress().getSuburb(), ride.getDropOffAddress().getSuburb());
    }

    public String rideRequestAmountMessage(Ride ride) {
        return String.format("Ride price has been updated to %.2f %s", ride.getPayment().getAmount().getValue().doubleValue(), ride.getPayment().getAmount().getCurrency());
    }

    public Set<Driver> findAvailableDrivers(String country) {
        return driverRepository.findByIsAvailableTrueAndStatusAndAddress_CountryIgnoreCase(Status.ACTIVE, country);
    }

    public Ride findRideById(String rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(() -> new NotFoundException(StringUtils.notFound(RideConstants.RIDE)));
    }

    public void updateRide(Ride ride,
                           RideStatus rideStatus,
                           String body,
                           String title) {

        ride.setStatus(rideStatus);
        rideRepository.save(ride);

        broadcastFirebaseNotification(
                StringUtils.commaSeparateCollection(getRideNotificationTokens(ride)),
                body,
                title
        );

    }

    public Set<String> getRideNotificationTokens(Ride ride) {
        Set<String> tokens = StringUtils.getPassengerNotificationTokens(ride.getPassengers());
        tokens.addAll(ride.getDriver().getUser().getDeviceTokens());
        return tokens;
    }

    public void broadcastFirebaseNotification(
            String tokens,
            String body,
            String title
    ) {
        messengerService.send(
                new MessengerRequest(
                        MessageType.FIRE_BASE,
                        tokens,
                        body,
                        title,
                        null
                )
        );

    }

}
