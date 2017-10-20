package cl.intelliware.smartlab.repositories;

import cl.intelliware.smartlab.models.Assignment;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
}