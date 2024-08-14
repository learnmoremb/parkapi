package zw.co.dreamhub.domain.repositories.ride;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.ride.PaymentType;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, String> {
    boolean existsByNameIgnoreCase(String name);
}