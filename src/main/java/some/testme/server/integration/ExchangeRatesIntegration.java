package some.testme.server.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import some.testme.server.dto.integration.rates.ExchangeRatesResponse;
import some.testme.server.dto.integration.rates.Rates;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExchangeRatesIntegration {

	private final RestTemplate exchangeRatesRestTemplate;

	@Value("${integration.exchange-rate.url}")
	private String exchangeRatesUrl;

	public @Nullable Double getUsdRate() {
		ExchangeRatesResponse result = exchangeRatesRestTemplate.getForObject(exchangeRatesUrl, ExchangeRatesResponse.class);
		return Optional.ofNullable(result)
				.map(ExchangeRatesResponse::getRates)
				.map(Rates::getUsd)
				.orElse(null);
	}
}
