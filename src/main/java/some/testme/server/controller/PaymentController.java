package some.testme.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import some.testme.server.dto.ApiResult;
import some.testme.server.service.PaymentService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.security.Principal;

@Validated
@RestController
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

	@Operation(security = @SecurityRequirement(name = "Bearer"))
	@GetMapping("/set-amount")
	public ApiResult setAmount(
			Principal principal,
			@RequestParam @Min(-1) @Max(Integer.MAX_VALUE) Integer value
	) {
		String name = principal.getName();
		return paymentService.setAmount(name, value);
	}

	@Operation(security = @SecurityRequirement(name = "Bearer"))
	@GetMapping("/get-amount")
	public ApiResult getAmount(Principal principal) {
		String name = principal.getName();
		return paymentService.getAmount(name);
	}

	@Operation(security = @SecurityRequirement(name = "Bearer"))
	@GetMapping("/add-one")
	public ApiResult addOne(Principal principal) {
		String name = principal.getName();
		return paymentService.addOne(name);
	}
}
