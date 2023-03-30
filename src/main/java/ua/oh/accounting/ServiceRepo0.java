package ua.oh.accounting;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

//@Component
public class ServiceRepo0<T, R extends JpaRepository<T, V>, V> {

  private final R repository;

  public ServiceRepo0(R repository) {
    this.repository = repository;
  }

  public List<T> findAll() {
    return repository.findAll();
  }

  public Optional<T> findById(V id) {
    return repository.findById(id);
  }

  public T save(T entity) {
    repository.save(entity);
    return entity;
  }

  public void delete(T entity) {
    repository.delete(entity);
  }

}
