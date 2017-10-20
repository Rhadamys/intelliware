package cl.intelliware.smartlab.repositories;

import cl.intelliware.smartlab.models.TestCase;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TestCaseRepository extends CrudRepository<TestCase, Long> {
}