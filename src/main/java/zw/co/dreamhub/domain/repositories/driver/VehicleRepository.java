package zw.co.dreamhub.domain.repositories.driver;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.driver.Vehicle;
import zw.co.dreamhub.domain.projections.driver.VehicleInfo;

import java.util.Optional;
import java.util.Set;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    boolean existsByRegistrationNumberIgnoreCase(String registrationNumber);
    Page<VehicleInfo> findAllByOrderByRegistrationNumber(Pageable pageable);
    Optional<VehicleInfo> findByRegistrationNumber(String registrationNumber);
    Optional<VehicleInfo> findVehicleById(String id);
    Set<Vehicle> findByIdIn(Set<String> Ids);
}