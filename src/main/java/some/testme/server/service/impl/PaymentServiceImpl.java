package some.testme.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import some.testme.server.dto.ApiResult;
import some.testme.server.entity.UserEntity;
import some.testme.server.repository.UserRepository;
import some.testme.server.service.PaymentService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	private final ThreadLocal<Integer> value = ThreadLocal.withInitial(() -> 1);

	private final UserRepository userRepository;

	@Override
	public ApiResult setAmount(
			String name,
			Integer value
	) {
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

	@Override
	public ApiResult getAmount(String name) {
		UserEntity user = userRepository.getByUsername(name);
		return new ApiResult("Your amaunt of money now is " + user.getAmount());
	}


	@Override
	public ApiResult addOne(String name) {
		value.set(value.get() + 1);

		UserEntity user = userRepository.getByUsername(name);
		user.setAmount((double) value.get());
		userRepository.save(user);

		return new ApiResult("Your amaunt of money now is " + this.value.get());
	}
}
