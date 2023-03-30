package ua.oh.accounting.entity.document;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = "id")
public abstract class Document {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "date")
  private LocalDateTime date;

  @Column(name = "number")
  private String number;

  @Column(name = "is_delete_mark")
  private boolean isDeleteMark;

  @Column(name = "is_post")
  private String isPost;

}
