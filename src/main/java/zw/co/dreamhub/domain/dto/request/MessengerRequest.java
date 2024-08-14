package zw.co.dreamhub.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import zw.co.dreamhub.domain.models.enums.MessageType;

import java.io.File;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 14/1/2023
 */

public record MessengerRequest(@NotNull MessageType messageType,
                               @NotNull @NotEmpty String to,
                               @NotNull @NotEmpty String content,
                               @NotNull @NotEmpty String subject,
                               String from,
                               File file

) {
    public MessengerRequest(@NotNull MessageType messageType,
                            @NotNull @NotEmpty String to,
                            @NotNull @NotEmpty String content,
                            @NotNull @NotEmpty String subject,
                            String from) {
        this(messageType, to, content, subject, from, null);
    }

}
