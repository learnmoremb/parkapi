package zw.co.dreamhub.domain.projections.driver;

import zw.co.dreamhub.domain.models.enums.InsurancePolicyType;

import java.time.ZonedDateTime;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/19
 */
public interface InsuranceInfo {
    String getName();
    InsurancePolicyType getType();
    ZonedDateTime getExpiryDate();

}
