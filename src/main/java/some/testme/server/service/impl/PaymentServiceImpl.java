package some.testme.server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import some.testme.server.dto.ApiResult;
import some.testme.server.entity.UserEntity;
import some.testme.server.integration.ExchangeRatesIntegration;
import some.testme.server.repository.UserRepository;
import some.testme.server.service.PaymentService;

import java.math.BigDecimal;

@Slf4j
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

	@Override
	public ApiResult getAmount(String name) {
		UserEntity user = userRepository.getByUsername(name);

		Double usdRate;
		try {
			usdRate = exchangeRatesIntegration.getUsdRate();
		} catch (Exception e) {
			log.error("Got an error from exchange service: " + e.getMessage());
			return new ApiResult(String.format("not able to get exchange rates"));
		}

		double amount = user.getAmount();
		//todo prevent possible npe
		double amountInUsd = amount * usdRate;

		return new ApiResult(String.format("Your amaunt of money now is %s rub(%s usd)", amount, amountInUsd));
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
