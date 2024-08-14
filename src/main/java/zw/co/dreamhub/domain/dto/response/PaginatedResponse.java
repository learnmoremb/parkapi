package zw.co.dreamhub.domain.dto.response;

import java.util.List;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 20/9/2023
 */
public record PaginatedResponse<T>(
        long size,
        long total,
        long pageNumber,
        List<T> data
) {
}

