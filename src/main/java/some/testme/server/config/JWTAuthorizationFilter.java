package some.testme.server.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Configuration
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private static final String AUTHORITIES = "authorities";
	private static final String HEADER = "Authorization";
	private static final String PREFIX = "Bearer ";
	private static final String SECRET = "mySecretKey";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {
			setAuthOrClearContext(request);
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		}
	}

	private void setAuthOrClearContext(HttpServletRequest request) {
		if (checkJWTToken(request)) {
			Claims claims = validateToken(request);
			setAuthOrClearContext(claims);
		} else {
			SecurityContextHolder.clearContext();
		}
	}

	private void setAuthOrClearContext(Claims claims) {
		if (nonNull(claims.get(AUTHORITIES))) {
			setUpSpringAuthentication(claims);
		} else {
			SecurityContextHolder.clearContext();
		}
	}

	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		return Jwts.parser()
				.setSigningKey(SECRET.getBytes())
				.parseClaimsJws(jwtToken)
				.getBody();
	}

	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List<String>) claims.get(AUTHORITIES);

		List<SimpleGrantedAuthority> authorityList = authorities.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		var auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorityList);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private boolean checkJWTToken(HttpServletRequest request) {
		String authenticationHeader = request.getHeader(HEADER);
		return authenticationHeader != null && authenticationHeader.startsWith(PREFIX);
	}

}
