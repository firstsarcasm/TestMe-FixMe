package some.testme.server.integration;

import lombok.RequiredArgsConstructor;
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

	public @Nullable Double getUsdRate() {
		ExchangeRatesResponse result = exchangeRatesRestTemplate.getForObject("https://www.cbr-xml-daily.ru/latest.js", ExchangeRatesResponse.class);
		return Optional.ofNullable(result)
				.map(ExchangeRatesResponse::getRates)
				.map(Rates::getUsd)
				.orElse(null);
	}
}
