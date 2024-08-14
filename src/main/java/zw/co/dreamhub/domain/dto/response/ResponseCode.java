package zw.co.dreamhub.domain.dto.response;

import lombok.Getter;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 19/9/2023
 */
@Getter
public enum ResponseCode {

    SUCCESS("Request completed successfully"),
    ERROR("Request failed");

    private final String description;

    ResponseCode(String description) {
        this.description = description;
    }

}
