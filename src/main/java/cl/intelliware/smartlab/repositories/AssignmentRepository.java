package cl.intelliware.smartlab.repositories;

import cl.intelliware.smartlab.models.Assignment;
import cl.intelliware.smartlab.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
}