package cl.intelliware.smartlab.repositories;

import cl.intelliware.smartlab.models.Teacher;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}