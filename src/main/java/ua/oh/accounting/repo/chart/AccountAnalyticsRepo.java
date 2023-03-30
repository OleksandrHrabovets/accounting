package ua.oh.accounting.repo.chart;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.oh.accounting.entity.chart.AccountAnalytics;

public interface AccountAnalyticsRepo extends JpaRepository<AccountAnalytics, Long> {

  Optional<AccountAnalytics> findByName(String ClassName);

  Optional<AccountAnalytics> findByClassName(String ClassName);

}
