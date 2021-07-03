package some.testme.server.repository;

import org.springframework.data.repository.CrudRepository;
import some.testme.server.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	boolean existsByKey(String key);

	UserEntity getByUsername(String username);

}
