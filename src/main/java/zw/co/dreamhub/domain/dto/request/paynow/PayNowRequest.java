package zw.co.dreamhub.domain.dto.request.paynow;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 1/10/2023
 */
public record PayNowRequest(
        @NotNull
        double amount,
        @NotNull
        String reference,
        @NotNull
        MobileMoneyType type,
        @NotNull
        @NotBlank
        @Size(min = 10, max = 12)
        String mobile,
        @NotNull
        @NotBlank
        @Email
        String email
) {

    public enum MobileMoneyType {
        PAYNOW_ECOCASH, PAYNOW_ONE_MONEY, PAYNOW_INN_BUCKS
    }

}
