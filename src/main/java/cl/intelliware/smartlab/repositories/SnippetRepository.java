package cl.intelliware.smartlab.repositories;

import cl.intelliware.smartlab.models.Problem;
import cl.intelliware.smartlab.models.Snippet;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SnippetRepository extends CrudRepository<Snippet, Long> {
}