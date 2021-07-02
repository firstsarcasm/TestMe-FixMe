package some.testme.server.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiStatus {
	SUCCESS("success"), ERROR("error");

	private final String code;
}
