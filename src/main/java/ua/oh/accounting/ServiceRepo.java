package ua.oh.accounting;

import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceRepo {

  private final List<JpaRepository<?, ?>> list;

  @Autowired
  public ServiceRepo(List<JpaRepository<?, ?>> list) {
    this.list = list;
  }

  @PostConstruct
  public void run() {
    findAll();
  }

  public List findAll() {
    return list.get(1).findAll();
  }
//
//  public Optional<T> findById(V id) {
//    return repository.findById(id);
//  }
//
//  public T save(T entity) {
//    repository.save(entity);
//    return entity;
//  }
//
//  public void delete(T entity) {
//    repository.delete(entity);
//  }

}
