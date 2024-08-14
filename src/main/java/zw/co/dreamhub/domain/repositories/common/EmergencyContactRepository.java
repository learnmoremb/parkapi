package zw.co.dreamhub.domain.repositories.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.common.EmergencyContact;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, String> {
}