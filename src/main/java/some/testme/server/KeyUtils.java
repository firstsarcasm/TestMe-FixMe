package some.testme.server;

import lombok.NoArgsConstructor;
import some.testme.server.dto.User;
import some.testme.server.exception.ApiException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class KeyUtils {

	public static String extractKey(User user) {
		try {
			byte[] keyBytes = (user.getUsername() + "_" + user.getEmail() + "_" + user.getPwd()).getBytes(StandardCharsets.UTF_8);
			return toHashString(keyBytes);
		} catch (Exception e) {
			throw ApiException.internal("Hashing error");
		}

	}

	private static String toHashString(byte[] keyBytes) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(keyBytes);
		return Base64.getEncoder().encodeToString(hash);
	}

}
