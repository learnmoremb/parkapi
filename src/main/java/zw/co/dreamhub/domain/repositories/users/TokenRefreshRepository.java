package zw.co.dreamhub.domain.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.users.TokenRefresh;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TokenRefreshRepository extends JpaRepository<TokenRefresh, String> {

    long deleteAllByIsValidFalse();

    Optional<TokenRefresh> findByTokenAndIsValidTrue(UUID token);

}