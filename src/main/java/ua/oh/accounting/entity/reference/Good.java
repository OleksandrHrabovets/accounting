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
@Table(name = "ref_good")
public class Good extends Reference {

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "description")
  private String description;

}
