package zw.co.dreamhub.services.driver;

import org.springframework.stereotype.Service;
import zw.co.dreamhub.domain.models.common.Media;
import zw.co.dreamhub.domain.models.driver.Vehicle;

import java.util.Set;

@Service
public class VehicleImageOperations implements ImageOperations<Vehicle> {
    @Override
    public void addImages(Vehicle entity, Set<Media> images) {
        entity.getImages().addAll(images);
    }
}