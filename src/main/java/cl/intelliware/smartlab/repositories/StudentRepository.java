package cl.intelliware.smartlab.repositories;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface StudentRepository extends CrudRepository<Student, Long> {

}