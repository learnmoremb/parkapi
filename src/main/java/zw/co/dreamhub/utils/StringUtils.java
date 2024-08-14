package zw.co.dreamhub.utils;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import zw.co.dreamhub.domain.models.passenger.Passenger;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 15/3/2023
 */
@UtilityClass
@Getter
public class StringUtils {

    public static final String ACCESS_TOKEN_TYPE = "Bearer";

    public static final String SUCCESS = "Success";
    public static final String CREATED = "Created";
    public static final String ERROR = "Error";
    public static final String BAD_REQUEST = "Bad request, please try again later";


    public static final String SAME_PASSWORD = "Old and new passwords are similar";
    public static final String DIFFERENT_PASSWORDS = "Passwords are not matching";

    public static String alreadyExists(String name) {
        return String.format("%s already exists", name);
    }

    public static String notFound(String name) {
        return String.format("%s not found", name);
    }

    public static String passwordMessage(String password) {
        return String.format("Your new password is : %s", password);
    }

    public static String commaSeparateCollection(Collection<String> elements) {
        StringBuilder results = new StringBuilder();
        elements.forEach(element -> results.append(String.format("%s ,", element)));
        return results.toString();
    }

    public static Set<String> getPassengerNotificationTokens(Set<Passenger> passengers) {
        return passengers
                .stream()
                .map(passenger -> passenger.getUser().getDeviceTokens())
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

}
