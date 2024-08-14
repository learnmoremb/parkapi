package zw.co.dreamhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 6/12/2023
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message);
    }
}
