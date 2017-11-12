package cl.intelliware.smartlab.repositories;

import cl.intelliware.smartlab.models.Role;
import cl.intelliware.smartlab.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findUsersByRolesContains(Role role);
    User findByMail(String email);
}