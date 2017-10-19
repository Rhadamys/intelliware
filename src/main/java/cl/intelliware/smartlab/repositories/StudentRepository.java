package cl.intelliware.smartlab.repositories;

import cl.intelliware.smartlab.models.Student;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface StudentRepository extends CrudRepository<Student, Long> {
}