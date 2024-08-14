package zw.co.dreamhub.domain.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.users.UserDetail;


@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, String> {
}