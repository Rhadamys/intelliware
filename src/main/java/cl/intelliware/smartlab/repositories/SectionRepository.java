package cl.intelliware.smartlab.repositories;

import cl.intelliware.smartlab.models.Section;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SectionRepository extends CrudRepository<Section, Long> {
}