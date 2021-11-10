package some.testme.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import some.testme.server.dto.ApiResult;
import some.testme.server.entity.UserEntity;
import some.testme.server.integration.ExchangeRatesIntegration;
import some.testme.server.repository.UserRepository;
import some.testme.server.service.PaymentService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	private final ThreadLocal<Integer> value = ThreadLocal.withInitial(() -> 1);

	private final UserRepository userRepository;
	private final ExchangeRatesIntegration exchangeRatesIntegration;

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
		return new ApiResult("Your amaunt of money now is " + BDValue);
	}

	//todo add documentation about rates integration
	@Override
	public ApiResult getAmount(String name) {
		UserEntity user = userRepository.getByUsername(name);
		Double usdRate = exchangeRatesIntegration.getUsdRate();

		double amount = user.getAmount();
		//todo prevent possible npe
		double amountInUsd = amount * usdRate;

		return new ApiResult(String.format("Your amaunt of money now is %s rub(%s usd)", amount, amountInUsd));
	}


	@Override
	public ApiResult addOne(String name) {
		UserEntity user = userRepository.getByUsername(name);
		double amount = user.getAmount();
		user.setAmount(++amount);
		userRepository.save(user);

		return new ApiResult("Your amaunt of money now is " + amount);
	}
}
