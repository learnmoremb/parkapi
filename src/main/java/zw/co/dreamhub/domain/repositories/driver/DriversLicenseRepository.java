package zw.co.dreamhub.domain.repositories.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.driver.DriversLicense;
import zw.co.dreamhub.domain.projections.driver.DriversLicenseInfo;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DriversLicenseRepository extends JpaRepository<DriversLicense, String> {
    boolean existsDriversLicenseByLicenseNumber(String licenseNumber);

    Optional<DriversLicenseInfo> findDriversLicenseByLicenseNumber(String licenseNumber);

    Set<DriversLicense> findByIdIn(Set<String> ids);

}