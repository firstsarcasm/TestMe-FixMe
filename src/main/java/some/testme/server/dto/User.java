package some.testme.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@NotEmpty
	@Size(max = 500, min = 5)
	private String username;

	@Size(max = 500, min = 5)
	@NotEmpty
	private String pwd;

	@Email
	@NotEmpty
	@Size(max = 500, min = 5)
	private String email;
}
