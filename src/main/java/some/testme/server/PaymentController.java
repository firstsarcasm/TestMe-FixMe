package some.testme.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import some.testme.server.dto.PaymentResult;

import java.math.BigDecimal;

//todo add spring security
//todo add validation
//todo add correlationId
//todo add swagger
//todo add actuator
@RestController
public class PaymentController {
//	private final ThreadLocal<Integer> value = ThreadLocal.withInitial(() -> 0);
	private double value = 0;

	@GetMapping("/set-amount")
	public PaymentResult setAmount(
			@RequestParam int value
	) {
		this.value = value;
		return new PaymentResult("Your amaunt of money now is " + value);
	}

	@GetMapping("/get-amount")
	public PaymentResult getAmount() {
		return new PaymentResult("Your amaunt of money now is " + this.value);
	}

	@GetMapping("/add-one")
	public PaymentResult addOne() {
		this.value++;
		return new PaymentResult("Your amaunt of money now is " + this.value);
	}
}
