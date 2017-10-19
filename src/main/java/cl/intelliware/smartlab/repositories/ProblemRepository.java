package cl.intelliware.smartlab.repositories;

import cl.intelliware.smartlab.models.Problem;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ProblemRepository extends CrudRepository<Problem, Long> {
}