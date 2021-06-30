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
	private final ThreadLocal<Integer> value = ThreadLocal.withInitial(() -> 0);
	//todo fix with the solution
//	private int value = 0;

	@GetMapping("/set-amount")
	public PaymentResult setAmount(
			@RequestParam int value
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
