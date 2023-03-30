package ua.oh.accounting.entity.chart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ua.oh.accounting.enums.TypeAccount;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "accountAnalytics")
@EqualsAndHashCode(of = "id")
@Table(name = "account")
public class Account {

  @Id
  @Column(name = "id", unique = true)
  private String id;

  @Column(name = "parent_id")
  private String parentId;

  @Column(name = "is_group")
  private boolean isGroup;

  @Column(name = "is_delete_mark")
  private boolean isDeleteMark;

  @Column(name = "is_quantitative")
  private boolean isQuantitative;

  @Column(name = "is_multi_currency")
  private boolean isMultiCurrency;

  @Column(name = "name")
  private String name;

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private TypeAccount type;

  @ManyToMany(mappedBy = "accounts", fetch = FetchType.EAGER)
  private Set<AccountAnalytics> accountAnalytics;
}
