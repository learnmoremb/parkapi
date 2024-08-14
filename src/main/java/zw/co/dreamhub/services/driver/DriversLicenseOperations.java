package zw.co.dreamhub.services.driver;

import org.springframework.stereotype.Service;
import zw.co.dreamhub.domain.models.common.Media;
import zw.co.dreamhub.domain.models.driver.DriversLicense;

import java.util.Set;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/15
 */
@Service
public class DriversLicenseOperations implements ImageOperations<DriversLicense>{
    @Override
    public void addImages(DriversLicense entity, Set<Media> images) {
        entity.getImages().addAll(images);
    }
}
