package ua.oh.accounting.repo.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.oh.accounting.entity.reference.Organization;

public interface OrganizationRepo extends JpaRepository<Organization, Long> {

}
