package zw.co.dreamhub.domain.repositories.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.dreamhub.domain.models.common.ExchangeRate;
import zw.co.dreamhub.domain.models.enums.Currency;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, String> {

    Optional<ExchangeRate> findFirstByAmount_CurrencyOrderByDateCreatedDesc(Currency currency);

}