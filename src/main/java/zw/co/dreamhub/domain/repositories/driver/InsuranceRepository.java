package zw.co.dreamhub.domain.repositories.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.driver.Insurance;
import zw.co.dreamhub.domain.models.driver.Vehicle;
import zw.co.dreamhub.domain.projections.driver.InsuranceInfo;

import java.util.Optional;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, String> {
    boolean existsByNameIgnoreCase(String name);
    Optional<InsuranceInfo> findInsuranceByVehicle_Id(String vehicleId);


}