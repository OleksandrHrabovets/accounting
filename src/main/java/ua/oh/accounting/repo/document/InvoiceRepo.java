package ua.oh.accounting.repo.document;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.oh.accounting.entity.document.Invoice;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

}
