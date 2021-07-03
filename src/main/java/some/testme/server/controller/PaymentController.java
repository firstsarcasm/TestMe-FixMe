package some.testme.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import some.testme.server.dto.ApiResult;
import some.testme.server.entity.UserEntity;
import some.testme.server.repository.UserRepository;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.security.Principal;

@Validated
@RestController
@RequiredArgsConstructor
//todo move logic to service
public class PaymentController {

	private final ThreadLocal<Integer> value = ThreadLocal.withInitial(() -> 1);

	private final UserRepository userRepository;

	@GetMapping("/set-amount")
	public ApiResult setAmount(
			Principal principal,
			@RequestParam @Min(-1) @Max(Integer.MAX_VALUE) Integer value
	) {
		String name = principal.getName();
		this.value.set(value);

		UserEntity user = userRepository.getByUsername(name);
		user.setAmount((double) value);
		userRepository.save(user);

		BigDecimal BDValue = BigDecimal.valueOf(value);
		if (BDValue != null) {
			if (BDValue.abs().multiply(BigDecimal.TEN)
					.compareTo(BigDecimal.valueOf(50)) >= 0) {
				BDValue = BDValue.add(BigDecimal.ONE);
			}
		}
		return new ApiResult("Your amaunt of money now is " + BDValue);
	}

	@GetMapping("/get-amount")
	public ApiResult getAmount(Principal principal) {
		String name = principal.getName();
		UserEntity user = userRepository.getByUsername(name);
		return new ApiResult("Your amaunt of money now is " + user.getAmount());
	}

	@GetMapping("/add-one")
	public ApiResult addOne(Principal principal) {
		String name = principal.getName();
		value.set(value.get() + 1);

		UserEntity user = userRepository.getByUsername(name);
		user.setAmount((double) value.get());
		userRepository.save(user);

		return new ApiResult("Your amaunt of money now is " + this.value.get());
	}
}
