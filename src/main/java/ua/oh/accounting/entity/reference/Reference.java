package ua.oh.accounting.entity.reference;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = "id")
public abstract class Reference {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "parent_id")
  private String parentId;

  @Column(name = "is_group")
  private boolean isGroup;

  @Column(name = "is_delete_mark")
  private boolean isDeleteMark;

  @Column(name = "name")
  private String name;

}
