package zw.co.dreamhub.domain.projections.driver;

import zw.co.dreamhub.domain.projections.MediaInfo;

import java.util.Set;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/14
 */
public interface VehicleInfo {
    String getMake();

    String getModel();

    long getYear();

    String getColor();

    String getDescription();

    long capacity();

    String registrationNumber();

    VehicleCategoryInfo getCategory();

    double getEngineSize();

    Set<MediaInfo> getImages();
}
