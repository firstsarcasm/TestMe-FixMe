package some.testme.server.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import some.testme.server.dto.ApiResult;
import some.testme.server.dto.User;
import some.testme.server.service.UserService;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("get-token")
	public ResponseEntity<ApiResult> getToken(@Valid @RequestBody User user) {
		ApiResult result = userService.getToken(user);
		return ResponseEntity.ok(result);
	}

	@PostMapping("register")
	public ResponseEntity<ApiResult> register(@Valid @RequestBody User user) {
		ApiResult result = userService.register(user);
		return ResponseEntity.ok(result);
	}


}