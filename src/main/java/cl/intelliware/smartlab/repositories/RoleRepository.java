package cl.intelliware.smartlab.repositories;

import cl.intelliware.smartlab.models.Role;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoleRepository extends CrudRepository<Role, Long> {
}