package ua.oh.accounting.entity.accounting;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ua.oh.accounting.entity.chart.Account;
import ua.oh.accounting.entity.chart.AccountAnalytics;
import ua.oh.accounting.entity.reference.Currency;
import ua.oh.accounting.entity.reference.Organization;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "entry")
public class Entry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "operation_id")
  private Operation operation;

  @Column(name = "date")
  private LocalDateTime date;

  @OneToOne
  @JoinColumn(name = "organization_id", referencedColumnName = "id")
  private Organization organization;

  @OneToOne
  @JoinColumn(name = "debit_account_id", referencedColumnName = "id")
  private Account debitAccount;

  @OneToOne
  @JoinColumn(name = "credit_account_id", referencedColumnName = "id")
  private Account creditAccount;

  @JoinColumn(name = "sum", referencedColumnName = "id")
  private BigDecimal sum;

  @Column(name = "quantity")
  private BigDecimal quantity;

  @OneToOne
  @JoinColumn(name = "currency_id", referencedColumnName = "id")
  private Currency currency;

  @Column(name = "sum_currency")
  private BigDecimal sumCurrency;

  @OneToOne
  @JoinColumn(name = "debit_account_Analytics1_id", referencedColumnName = "id")
  private AccountAnalytics debitAccountAnalytics1;

  @OneToOne
  @JoinColumn(name = "debit_account_Analytics2_id", referencedColumnName = "id")
  private AccountAnalytics debitAccountAnalytics2;

  @OneToOne
  @JoinColumn(name = "debit_account_Analytics3_id", referencedColumnName = "id")
  private AccountAnalytics debitAccountAnalytics3;

  @Column(name = "debit_account_Analytics1_value")
  private Long debitAccountAnalytics1Value;

  @Column(name = "debit_account_Analytics2_value")
  private Long debitAccountAnalytics2Value;

  @Column(name = "debit_account_Analytics3_value")
  private Long debitAccountAnalytics3Value;


  @OneToOne
  @JoinColumn(name = "credit_account_Analytics1_id", referencedColumnName = "id")
  private AccountAnalytics creditAccountAnalytics1;

  @OneToOne
  @JoinColumn(name = "credit_account_Analytics2_id", referencedColumnName = "id")
  private AccountAnalytics creditAccountAnalytics2;

  @OneToOne
  @JoinColumn(name = "credit_account_Analytics3_id", referencedColumnName = "id")
  private AccountAnalytics creditAccountAnalytics3;

  @Column(name = "credit_account_Analytics1_value")
  private Long creditAccountAnalytics1Value;

  @Column(name = "credit_account_Analytics2_value")
  private Long creditAccountAnalytics2Value;

  @Column(name = "credit_account_Analytics3_value")
  private Long creditAccountAnalytics3Value;


}

