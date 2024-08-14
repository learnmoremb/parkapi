package zw.co.dreamhub.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.dto.response.PaginatedResponse;
import zw.co.dreamhub.domain.dto.response.ResponseCode;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 19/9/2023
 */
@UtilityClass
public class ResponseUtils {

    public <T> ApiResponse<T> success(T body) {
        return new ApiResponse<>(
                true, ResponseCode.SUCCESS, HttpStatus.OK.value(), body
        );
    }

    public <T> ApiResponse<T> created(T body) {
        return new ApiResponse<>(
                true, ResponseCode.SUCCESS, HttpStatus.CREATED.value(), body
        );
    }

    public <T> ApiResponse<T> badRequest(T body) {
        return new ApiResponse<>(
                false, ResponseCode.ERROR, HttpStatus.BAD_REQUEST.value(), body
        );
    }

    public <T> ApiResponse<T> badRequest(T body, int status) {
        return new ApiResponse<>(
                false, ResponseCode.ERROR, status, body
        );
    }

    public <T> ResponseEntity<ApiResponse<T>> respond(ApiResponse<T> response) {
        return ResponseEntity.status(response.status()).body(response);
    }

    public <T> PaginatedResponse<T> pageResponse(Page<T> data, long pageNumber) {
        return new PaginatedResponse<>(
                data.getContent().size(),
                data.getTotalElements(),
                pageNumber,
                data.getContent()
        );
    }

}
