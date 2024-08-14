package zw.co.dreamhub.domain.projections.driver;

import zw.co.dreamhub.domain.models.enums.Status;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/14
 */
public interface VehicleCategoryInfo {
    String getName();

    String getDescription();

    Status getStatus();

}
