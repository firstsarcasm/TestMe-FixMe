package some.testme.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import some.testme.server.constants.ApiStatus;
import some.testme.server.dto.ApiError;
import some.testme.server.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Configuration
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

	//todo make custom handlers for different exceptions

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
				ApiError.builder()
						.status(ApiStatus.ERROR)
						.message(ex.getMessage())
						.build());
	}

	@ExceptionHandler(ApiException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(ApiException ex, WebRequest request) {
		return ResponseEntity.status(ex.getHttpStatus()).body(ex.getApiError());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest().body(
				ApiError.builder()
						.status(ApiStatus.ERROR)
						.message(ex.getMessage())
						.build());
	}
}

