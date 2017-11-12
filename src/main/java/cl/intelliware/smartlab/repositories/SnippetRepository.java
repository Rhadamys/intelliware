package cl.intelliware.smartlab.repositories;

import cl.intelliware.smartlab.models.Problem;
import cl.intelliware.smartlab.models.Snippet;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SnippetRepository extends CrudRepository<Snippet, Long> {
  public List<Snippet> findAllByOrderByIdDesc();
}