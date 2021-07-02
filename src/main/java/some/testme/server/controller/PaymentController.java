package some.testme.server.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import some.testme.server.dto.PaymentResult;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

//todo add db for users
//todo add proper spring security
//todo improve exception handling
@Validated
@RestController
public class PaymentController {
	private final ThreadLocal<Integer> value = ThreadLocal.withInitial(() -> 1);

	@GetMapping("/set-amount")
	public PaymentResult setAmount(
			@RequestParam @Min(0) @Max(Integer.MAX_VALUE) Integer value
	) {
		this.value.set(value);
		BigDecimal BDValue = BigDecimal.valueOf(value);
		if (BDValue != null) {
			if (BDValue.abs().multiply(BigDecimal.TEN)
					.compareTo(BigDecimal.valueOf(50)) >= 0) {
				BDValue = BDValue.add(BigDecimal.ONE);
			}
		}
		return new PaymentResult("Your amaunt of money now is " + BDValue);
	}

	@GetMapping("/get-amount")
	public PaymentResult getAmount() {
		return new PaymentResult("Your amaunt of money now is " + this.value.get());
	}

	@GetMapping("/add-one")
	public PaymentResult addOne() {
		value.set(value.get() + 1);
		return new PaymentResult("Your amaunt of money now is " + this.value.get());
	}
}
