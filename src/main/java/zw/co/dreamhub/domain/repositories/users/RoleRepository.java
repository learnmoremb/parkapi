package zw.co.dreamhub.domain.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.users.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}