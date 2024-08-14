package zw.co.dreamhub.domain.projections.driver;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/15
 */
public interface DriversLicenseInfo {
    String getLicenseNumber();
    LocalDate getDateObtained();
    ZonedDateTime getExpiryDate();
    String getCountry();
}
