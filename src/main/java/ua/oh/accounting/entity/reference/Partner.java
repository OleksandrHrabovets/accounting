package ua.oh.accounting.entity.reference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ref_partner")
public class Partner extends Reference {

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "registration_code")
  private String registrationCode;

  @Column(name = "address")
  private String address;

  @Column(name = "physical_address")
  private String physicalAddress;
}
