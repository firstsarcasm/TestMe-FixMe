package some.testme.server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import some.testme.server.constants.ApiStatus;
import some.testme.server.dto.ApiError;

@Getter
@RequiredArgsConstructor
public class ApiException extends RuntimeException {
	private final ApiError apiError;
	private final HttpStatus httpStatus;

	public static ApiException badRequest(String message) {
		return new ApiException(ApiError.builder()
				.message(message)
				.status(ApiStatus.ERROR)
				.build(), HttpStatus.BAD_REQUEST);
	}
}
