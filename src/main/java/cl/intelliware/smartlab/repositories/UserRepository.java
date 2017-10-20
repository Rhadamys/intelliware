package cl.intelliware.smartlab.repositories;

import cl.intelliware.smartlab.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
}