package zw.co.dreamhub.domain.repositories.passenger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.passenger.Passenger;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {
    Optional<Passenger> findPassengerByUser_Id(String userId);

    Set<Passenger> findByUser_IdIn(Collection<String> ids);
}