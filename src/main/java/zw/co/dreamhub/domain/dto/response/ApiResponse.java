package zw.co.dreamhub.domain.dto.response;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 17/9/2023
 */
public record ApiResponse<T>(
        Boolean successful,
        ResponseCode narration,
        int status,
        T body
) {
}
