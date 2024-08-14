package zw.co.dreamhub.domain.repositories.ride;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.ride.RideType;


@Repository
public interface RideTypeRepository extends JpaRepository<RideType, String> {
}