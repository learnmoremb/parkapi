package zw.co.dreamhub.domain.repositories.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.driver.Driver;
import zw.co.dreamhub.domain.models.enums.Status;
import zw.co.dreamhub.domain.models.users.User;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DriverRepository extends JpaRepository<Driver, String> {
    Optional<Driver> findDriverByUser_Id(String userId);

    Set<Driver> findByIsAvailableTrueAndStatusAndAddress_CountryIgnoreCase(Status status, String country);
}