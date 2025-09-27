package com.latambank.forex.repository;

import com.latambank.forex.entity.FxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FxRateRepository extends JpaRepository<FxRate, Long> {
    Optional<FxRate> findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
}
