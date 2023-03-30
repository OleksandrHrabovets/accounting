package ua.oh.accounting;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.oh.accounting.entity.accounting.Entry;
import ua.oh.accounting.entity.accounting.Operation;
import ua.oh.accounting.entity.chart.Account;
import ua.oh.accounting.entity.chart.AccountAnalytics;
import ua.oh.accounting.entity.reference.Good;
import ua.oh.accounting.entity.reference.Organization;
import ua.oh.accounting.entity.reference.Partner;
import ua.oh.accounting.entity.reference.Reference;
import ua.oh.accounting.enums.TypeAccount;
import ua.oh.accounting.repo.accounting.EntryRepo;
import ua.oh.accounting.repo.accounting.OperationRepo;
import ua.oh.accounting.repo.chart.AccountAnalyticsRepo;
import ua.oh.accounting.repo.chart.AccountRepo;
import ua.oh.accounting.repo.reference.GoodRepo;
import ua.oh.accounting.repo.reference.OrganizationRepo;
import ua.oh.accounting.repo.reference.PartnerRepo;

@Slf4j
@Component
public class TestData {

  private final AccountRepo accountRepo;
  private final AccountAnalyticsRepo accountAnalyticsRepo;
  private final OrganizationRepo organizationRepo;
  private final OperationRepo operationRepo;
  private final EntryRepo entryRepo;
  private final PartnerRepo partnerRepo;
  private final GoodRepo goodRepo;

  public TestData(AccountRepo accountRepo, AccountAnalyticsRepo accountAnalyticsRepo,
      OrganizationRepo organizationRepo,
      OperationRepo operationRepo, EntryRepo entryRepo, PartnerRepo partnerRepo, GoodRepo goodRepo) {
    this.accountRepo = accountRepo;
    this.accountAnalyticsRepo = accountAnalyticsRepo;
    this.organizationRepo = organizationRepo;
    this.operationRepo = operationRepo;
    this.entryRepo = entryRepo;
    this.partnerRepo = partnerRepo;
    this.goodRepo = goodRepo;
  }


  @PostConstruct
  public void fillData() {
    if (accountAnalyticsRepo.findAll().isEmpty()) {
      createAccountAnalytics();
    }

    Map<String, Account> accounts = new HashMap<>();
    accounts.put("28", new Account("28", null, false, false,
        true, false, "Goods",
        TypeAccount.ACTIVE, null));
    accounts.put("36", new Account("36", null, false, false,
        false, false, "Settlements with buyers",
        TypeAccount.ACTIVE_PASSIVE, null));
    accounts.put("63", new Account("63", null, false, false,
        false, false, "Settlements with suppliers",
        TypeAccount.ACTIVE_PASSIVE, null));
    accounts.put("70", new Account("70", null, false, false,
        false, false, "Income from sales",
        TypeAccount.PASSIVE, null));
    accounts.put("90", new Account("90", null, false, false,
        false, false, "Cost of goods sold",
        TypeAccount.ACTIVE, null));
    accounts.keySet().forEach(id ->
        accountRepo.findById(id).orElseGet(() -> createAccount(accounts.get(id))));

    partnerRepo.findById(1L).orElseGet(
        () -> {
          Partner partner = new Partner();
          partner.setName("Partner 1");
          partner.setRegistrationCode("1111111111");
          partner.setAddress("Lutsk, Sobornosti st, 1");
          log.info("Create new partner {}", partner);
          return partnerRepo.save(partner);
        }
    );

    goodRepo.findById(1L).orElseGet(
        () -> {
          Good good = new Good();
          good.setName("Good 1");
          good.setDescription("First good in database");
          log.info("Create new partner {}", good);
          return goodRepo.save(good);
        }
    );


    organizationRepo.findById(1L).orElseGet(
        () -> {
          Organization organization = new Organization();
          organization.setName("My organization");
          organization.setRegistrationCode("0000000001");
          organization.setAddress("Lutsk, Voli st, 1");
          log.info("Create new organization {}", organization);
          return organizationRepo.save(organization);
        }
    );

    createOperation();


  }

  private void createAccountAnalytics() {
    AccountAnalytics accountAnalytics = new AccountAnalytics();
    accountAnalytics.setName("Partner");
    accountAnalytics.setClassName(Partner.class.getName());
    accountAnalyticsRepo.save(accountAnalytics);

    accountAnalytics = new AccountAnalytics();
    accountAnalytics.setName("Good");
    accountAnalytics.setClassName(Good.class.getName());
    accountAnalyticsRepo.save(accountAnalytics);
  }

  @Transactional
  private void createOperation() {
    Operation operation = new Operation();
    operation.setDate(LocalDateTime.now());
    operation.setNumber("0000000001");
    List<Entry> entries = new ArrayList<>();
    BigDecimal totalSum = BigDecimal.valueOf(0.00);

    Organization organization = organizationRepo.findById(1L).orElse(null);

    BigDecimal sum = BigDecimal.valueOf(10000.00);
    Account debitAccount = accountRepo.findById("36").orElse(null);
    Account creditAccount = accountRepo.findById("70").orElse(null);
    HashSet<Reference> debitAccountAnalytics = new HashSet();
    HashSet<Reference> creditAccountAnalytics = new HashSet();
    debitAccountAnalytics.add(partnerRepo.findById(1L).orElse(null));
    totalSum = createEntry(operation, entries, totalSum, sum, organization,
        debitAccount,creditAccount,
        debitAccountAnalytics,
        creditAccountAnalytics);

    sum = BigDecimal.valueOf(9000.00);
    debitAccount = accountRepo.findById("90").orElse(null);
    creditAccount = accountRepo.findById("28").orElse(null);
    debitAccountAnalytics = new HashSet();
    creditAccountAnalytics = new HashSet();
    creditAccountAnalytics.add(goodRepo.findById(1L).orElse(null));
    totalSum = createEntry(operation, entries, totalSum, sum, organization,
        debitAccount,creditAccount,
        debitAccountAnalytics,
        creditAccountAnalytics);

    operation.setEntries(entries);
    operation.setSum(totalSum);
    operationRepo.save(operation);
    entryRepo.saveAll(entries);
  }

  private BigDecimal createEntry(Operation operation, List<Entry> entries,
      BigDecimal totalSum, BigDecimal sum, Organization organization,
      Account debitAccount, Account creditAccount,
      Set<Reference> debitAccountAnalytics, Set<Reference> creditAccountAnalytics) {
    Entry entry = new Entry();
    entry.setOperation(operation);
    entry.setDate(operation.getDate());
    entry.setOrganization(organization);
    entry.setDebitAccount(debitAccount);
    entry.setCreditAccount(creditAccount);
    entry.setSum(sum);

    setDebitAccountAnalytics(entry, debitAccount, debitAccountAnalytics);
    setCreditAccountAnalytics(entry, creditAccount, creditAccountAnalytics);

    totalSum = totalSum.add(sum);
    entries.add(entry);
    return totalSum;
  }

  private void setDebitAccountAnalytics(Entry entry, Account account, Set<Reference> setAccountAnalytics) {
    Set<AccountAnalytics> accountAnalytics = getAccountAnalytics(account);
    for (AccountAnalytics a: accountAnalytics) {
      entry.setDebitAccountAnalytics1(a);
      for (Reference v: setAccountAnalytics) {
        if (a.getClassName().equals(v.getClass().getName())) {
          entry.setDebitAccountAnalytics1Value(v.getId());
        }
      }
    }
  }

  private void setCreditAccountAnalytics(Entry entry, Account account, Set<Reference> setAccountAnalytics) {
    Set<AccountAnalytics> accountAnalytics = getAccountAnalytics(account);
    for (AccountAnalytics a: accountAnalytics) {
      entry.setCreditAccountAnalytics1(a);
      for (Reference v: setAccountAnalytics) {
        if (a.getClassName().equals(v.getClass().getName())) {
          entry.setCreditAccountAnalytics1Value(v.getId());
        }
      }
    }
  }

  @Transactional
  private Account createAccount(Account account) {
    accountRepo.save(account);
    fillAnalytics(account);
    log.info("Create new account {}", account);
    return account;
  }

  private void fillAnalytics(Account account) {
    Set<AccountAnalytics> accountAnalytics = getAccountAnalytics(
        account);

    if (account.getId().equals("36") || account.getId().equals("63")) {
      AccountAnalytics partner = accountAnalyticsRepo.findByName("Partner").orElse(null);
      accountAnalytics.add(partner);
      account.setAccountAnalytics(accountAnalytics);

      Set<Account> accounts = getAccounts(partner);
      accounts.add(account);
      partner.setAccounts(accounts);
      accountAnalyticsRepo.save(partner);
      accountRepo.save(account);
    } else if (account.getId().equals("28")) {
      AccountAnalytics good = accountAnalyticsRepo.findByName("Good").orElse(null);
      accountAnalytics.add(good);
      account.setAccountAnalytics(accountAnalytics);

      Set<Account> accounts = getAccounts(good);
      accounts.add(account);
      good.setAccounts(accounts);
      accountAnalyticsRepo.save(good);
      accountRepo.save(account);
    }
  }

  private static Set<AccountAnalytics> getAccountAnalytics(Account account) {
    Set<AccountAnalytics> accountAnalytics = account.getAccountAnalytics();
    if (accountAnalytics == null) {
      accountAnalytics = new HashSet<>();
    }
    return accountAnalytics;
  }

  private static Set<Account> getAccounts(AccountAnalytics good) {
    Set<Account> accounts = good.getAccounts();
    if (accounts == null) {
      accounts = new HashSet<>();
    }
    return accounts;
  }


}
