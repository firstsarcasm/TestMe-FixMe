package some.testme.server.service;

import some.testme.server.dto.ApiResult;
import some.testme.server.dto.User;

public interface UserService {
	ApiResult getToken(User user);

	ApiResult register(User user);
}
