package some.testme.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JWTAuthorizationFilter jwtAuthorizationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/get-token", "/register").permitAll()
				.antMatchers(HttpMethod.GET, "/swagger-ui/*", "/v3/api-docs/*", "/add-one", "/v3/api-docs").permitAll()
				.antMatchers(HttpMethod.GET, "/actuator", "/actuator/*").permitAll()
				.antMatchers("/h2-console", "/h2-console/*").permitAll()
				.anyRequest().authenticated();
	}
}

