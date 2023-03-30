package ua.oh.accounting.repo.chart;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.oh.accounting.entity.chart.Account;

public interface AccountRepo extends JpaRepository<Account, String> {

}
