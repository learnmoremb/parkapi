package zw.co.dreamhub.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.utils.ResponseUtils;
import zw.co.dreamhub.utils.StringUtils;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 6/12/2023
 */

@ControllerAdvice
@RestController
@Slf4j
public class ExceptionHandlerService {

    @ExceptionHandler(BaseException.class)
    public final ResponseEntity<ApiResponse<String>> handleException(BaseException exception, WebRequest request) {

        log.info("Request : ({}) exception : {}",
                ((ServletWebRequest) request).getRequest().getRequestURI(),
                exception.getMessage());

        if (exception instanceof AuthException) {
            return new ResponseEntity<>(
                    ResponseUtils.badRequest(exception.getMessage()),
                    HttpStatus.UNAUTHORIZED
            );
        } else {
            return new ResponseEntity<>(
                    ResponseUtils.badRequest(exception.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }

    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResponse<String>> handleUnknownException(Exception exception) {

        log.info("Request exception : {}",
                exception.getMessage());

        return new ResponseEntity<>(
                ResponseUtils.badRequest(StringUtils.BAD_REQUEST),
                HttpStatus.BAD_REQUEST
        );

    }

}
