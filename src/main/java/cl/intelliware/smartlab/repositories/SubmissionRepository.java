package cl.intelliware.smartlab.repositories;

import cl.intelliware.smartlab.models.Submission;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SubmissionRepository extends CrudRepository<Submission, Long> {
}