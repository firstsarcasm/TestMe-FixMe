package some.testme.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import some.testme.server.dto.User;
import some.testme.server.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "key", source = "key2")
	UserEntity map(User user, String key2);

	User map(UserEntity user);

}
