package zw.co.dreamhub.domain.repositories.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.driver.VehicleCategory;

import java.util.Optional;


@Repository
public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory, String> {

   Optional<VehicleCategory> findVehicleCategoryByNameIgnoreCase(String name);

   boolean existsVehicleCategoryByNameIgnoreCase(String name);
}