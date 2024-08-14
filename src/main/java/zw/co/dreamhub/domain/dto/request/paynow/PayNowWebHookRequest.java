package zw.co.dreamhub.domain.dto.request.paynow;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 1/10/2023
 */
public record PayNowWebHookRequest(
        @JsonProperty("reference") String id,
        @JsonProperty("paynowreference") String payNowReference,
        @JsonProperty("pollurl") String pollUrl, String status, String hash
) {
}
