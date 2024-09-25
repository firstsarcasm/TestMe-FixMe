package some.testme.server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import some.testme.server.dto.ApiResult;
import some.testme.server.entity.UserEntity;
import some.testme.server.exception.ApiException;
import some.testme.server.integration.ExchangeRatesIntegration;
import some.testme.server.repository.UserRepository;
import some.testme.server.service.PaymentService;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final UserRepository userRepository;
	private final ExchangeRatesIntegration exchangeRatesIntegration;

	@Override
	public ApiResult setAmount(
			String name,
			Integer value
	) {
		UserEntity user = userRepository.getByUsername(name);
		user.setAmount((double) value);
		userRepository.save(user);

		return new ApiResult("Your amount of money now is " + (double) value);
	}

	@Override
	public ApiResult getAmount(String name) {
		UserEntity user = userRepository.getByUsername(name);

		try {
			Double usdRate = exchangeRatesIntegration.getUsdRate();

			if(isNull(usdRate)) {
				throw ApiException.internal("Not able to get exchange rates");
			}

			double amount = user.getAmount();
			double amountInUsd = amount * usdRate;

			return new ApiResult(String.format("Your amount of money now is %s rub(%s usd)", amount, amountInUsd));
		} catch (Exception e) {
			log.error("Got an error from exchange service: " + e.getMessage());
			throw ApiException.internal("exchange rate service is not available");
		}
	}

	@Override
	public ApiResult addOne(String name) {
		UserEntity user = userRepository.getByUsername(name);

		double newAmount = user.getAmount() + 1;
		user.setAmount(newAmount);

		userRepository.save(user);

		return new ApiResult("Your amount of money now is " + newAmount);
	}
	
}
