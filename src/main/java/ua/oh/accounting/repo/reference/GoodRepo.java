package ua.oh.accounting.repo.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.oh.accounting.entity.reference.Good;

public interface GoodRepo extends JpaRepository<Good, Long> {

}
