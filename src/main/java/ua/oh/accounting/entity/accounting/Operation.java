package ua.oh.accounting.entity.accounting;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "entries")
@EqualsAndHashCode(of = "id")
@Table(name = "operation")
public class Operation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "date")
  private LocalDateTime date;

  @Column(name = "number")
  private String number;

  @Column(name = "sum")
  private BigDecimal sum;

  @OneToMany(mappedBy = "operation")
  private List<Entry> entries;

}

