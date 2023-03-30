package ua.oh.accounting.repo.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.oh.accounting.entity.reference.Partner;

public interface PartnerRepo extends JpaRepository<Partner, Long> {

}
