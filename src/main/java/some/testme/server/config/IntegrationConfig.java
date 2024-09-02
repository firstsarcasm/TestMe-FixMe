package some.testme.server.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.zalando.logbook.spring.LogbookClientHttpRequestInterceptor;

import java.util.List;

import static java.util.Collections.singletonList;

@Configuration
public class IntegrationConfig {

	private static final String APPLICATION = "application";
	private static final String JAVASCRIPT = "javascript";
	private static final List<MediaType> APPLICATION_JAVASCRIPT_MEDIA_TYPE = singletonList(new MediaType(APPLICATION, JAVASCRIPT));

	@Bean(name = "exchangeRatesRestTemplate")
	public RestTemplate restTemplate(LogbookClientHttpRequestInterceptor interceptor) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(APPLICATION_JAVASCRIPT_MEDIA_TYPE);

		return new RestTemplateBuilder()
				.additionalMessageConverters(converter)
				.additionalInterceptors(interceptor)
				.requestFactory(() -> new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()))
				.build();
	}
}
