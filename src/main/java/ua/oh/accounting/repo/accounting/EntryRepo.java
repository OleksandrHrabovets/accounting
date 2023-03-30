package ua.oh.accounting.repo.accounting;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.oh.accounting.entity.accounting.Entry;

public interface EntryRepo extends JpaRepository<Entry, Long> {

}
