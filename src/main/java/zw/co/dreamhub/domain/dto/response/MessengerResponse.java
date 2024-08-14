package zw.co.dreamhub.domain.dto.response;


import zw.co.dreamhub.domain.models.enums.MessengerProcessingStatus;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 14/1/2023
 */

public record MessengerResponse(MessengerProcessingStatus message, String description) {
}
