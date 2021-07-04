package some.testme.server.controller;

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

	@GetMapping("/set-amount")
	public ApiResult setAmount(
			Principal principal,
			@RequestParam @Min(-1) @Max(Integer.MAX_VALUE) Integer value
	) {
		String name = principal.getName();
		return paymentService.setAmount(name, value);
	}

	@GetMapping("/get-amount")
	public ApiResult getAmount(Principal principal) {
		String name = principal.getName();
		return paymentService.getAmount(name);
	}

	@GetMapping("/add-one")
	public ApiResult addOne(Principal principal) {
		String name = principal.getName();
		return paymentService.addOne(name);
	}
}
