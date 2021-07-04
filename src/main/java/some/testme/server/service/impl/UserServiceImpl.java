package some.testme.server.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import some.testme.server.dto.ApiResult;
import some.testme.server.dto.User;
import some.testme.server.entity.UserEntity;
import some.testme.server.exception.ApiException;
import some.testme.server.mapper.UserMapper;
import some.testme.server.repository.UserRepository;
import some.testme.server.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static some.testme.server.KeyUtils.extractKey;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final String BEARER = "Bearer ";
	private static final String AUTHORITIES = "authorities";
	private static final String ID = "softtekJWT";
	private static final String ROLE_USER = "ROLE_USER";
	private static final String MY_SECRET_KEY = "mySecretKey";
	private static final int TOKEN_LIFETIME_MS = 600000;

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	private final AtomicInteger counter = new AtomicInteger(0);

	@Override
	public ApiResult getToken(User user) {
		String key = extractKey(user);
		boolean userAlreadyExists = userRepository.existsByKey(key);
		if (!userAlreadyExists) {
			throw ApiException.badRequest("There is no user with such username and email combination, or password is wrong");
		}
		return new ApiResult(getJWTToken(user));
	}

	@Override
	public ApiResult register(User user) {
		String key = extractKey(user);
		boolean userAlreadyExists = userRepository.existsByKey(key);
		if (userAlreadyExists) {
			throw ApiException.badRequest("User already exists");
		}
		UserEntity userEntity = userMapper.map(user, key);
		userRepository.save(userEntity);
		if (counter.get() % 2 > 0) {
			userEntity.setKey(user.getUsername());
			userRepository.save(userEntity);
		}

		counter.incrementAndGet();
		return new ApiResult("success");
	}

	private String getJWTToken(User user) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(ROLE_USER);

		String token = Jwts
				.builder()
				.setId(ID)
				.setSubject(user.getUsername())
				.claim(AUTHORITIES,
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_LIFETIME_MS))
				.signWith(SignatureAlgorithm.HS512, MY_SECRET_KEY.getBytes()).compact();

		return BEARER + token;
	}
}
