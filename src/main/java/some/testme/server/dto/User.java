package some.testme.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@NotEmpty
	@NotNull
	@Size(max = 500, min = 5)
	private String username;


	@NotEmpty
	@NotNull
	@Size(max = 500, min = 5)
	private String pwd;

	@Email
	@NotEmpty
	@NotNull
	@Size(max = 500, min = 5)
	private String email;
}
