package ua.oh.accounting.repo.accounting;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.oh.accounting.entity.accounting.Operation;

public interface OperationRepo extends JpaRepository<Operation, Long> {

}
