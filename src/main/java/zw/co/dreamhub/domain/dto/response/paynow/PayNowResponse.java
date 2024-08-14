package zw.co.dreamhub.domain.dto.response.paynow;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 1/10/2023
 */
public record PayNowResponse(
        TransactionStatus status,
        String statusUrl,
        String paymentUrl
) {

    public enum TransactionStatus {
        SUCCESS, FAILED
    }

    public PayNowResponse(TransactionStatus status) {
        this(status, null, null);
    }

    public PayNowResponse(TransactionStatus status, String statusUrl) {
        this(status, statusUrl, null);
    }

}
