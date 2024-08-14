package zw.co.dreamhub.services.driver;

import zw.co.dreamhub.domain.models.common.Media;

import java.util.Set;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/15
 */

@FunctionalInterface
public interface ImageOperations <T>{
    void addImages(T entity, Set<Media> images);
}
