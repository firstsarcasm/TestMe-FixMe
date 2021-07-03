package some.testme.server;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import some.testme.server.dto.User;
import some.testme.server.entity.UserEntity;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class KeyUtils {
	private static final String MY_SECRET_KEY_FOR_BASE_64 = "SE";

	public static String extractKey(User user) {
		byte[] keyBytes = (user.getUsername() + "_" + user.getEmail() + "_" + user.getPwd()).getBytes(StandardCharsets.UTF_8);
		String key = new String(Base64.getEncoder().encode(keyBytes));
		return MY_SECRET_KEY_FOR_BASE_64 + key;
	}

//	public static String extractKey(UserEntity user) {
//		user.getKey()
//		byte[] keyBytes = (user.getUsername() + "_" + user.getEmail() + "_" + user.getPwd()).getBytes(StandardCharsets.UTF_8);
//		String key = new String(Base64.getEncoder().encode(keyBytes));
//		return MY_SECRET_KEY_FOR_BASE_64 + key;
//	}
}
