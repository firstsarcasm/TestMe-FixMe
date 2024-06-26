package some.testme.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@NotNull
	@NotEmpty
	@Size(max = 500, min = 5)
	private String username;


	@NotNull
	@NotEmpty
	@Size(max = 500, min = 5)
	private String pwd;

	@Email
	@NotNull
	@NotEmpty
	@Size(max = 500, min = 5)
	@Schema(example = "some@mail.com")
	private String email;

}
