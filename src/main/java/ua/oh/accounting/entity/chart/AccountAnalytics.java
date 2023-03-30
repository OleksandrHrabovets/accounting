package ua.oh.accounting.entity.chart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "account_analytics")
public class AccountAnalytics {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "class_name")
  private String className;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "account_analytics_account",
      joinColumns = @JoinColumn(name = "saccount_analytics_id"),
      inverseJoinColumns = @JoinColumn(name = "account_id"))
  private Set<Account> accounts;
}

