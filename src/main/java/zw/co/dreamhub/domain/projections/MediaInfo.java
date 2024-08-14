package zw.co.dreamhub.domain.projections;

import jakarta.persistence.Column;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/14
 */
public interface MediaInfo {
     String getPath();
     String getContentType();
     String getName();
}
