package ua.oh.accounting.entity.document;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "doc_invoice")
public class Invoice extends Document {


}
