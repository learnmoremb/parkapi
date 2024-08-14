package zw.co.dreamhub.domain.repositories.ride;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.ride.Ride;

import java.util.Optional;


@Repository
public interface RideRepository extends JpaRepository<Ride, String> {
    Optional<Ride> findByPayment_Id(String id);
}