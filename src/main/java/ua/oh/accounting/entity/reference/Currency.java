package ua.oh.accounting.entity.reference;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ref_currency")
public class Currency extends Reference {

}
