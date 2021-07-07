package some.testme.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Collections.singletonList;

@Configuration
public class IntegrationConfig {

	private static final String APPLICATION = "application";
	private static final String JAVASCRIPT = "javascript";
	private static final List<MediaType> APPLICATION_JAVASCRIPT_MEDIA_TYPE = singletonList(new MediaType(APPLICATION, JAVASCRIPT));

	@Bean(name = "exchangeRatesRestTemplate")
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(APPLICATION_JAVASCRIPT_MEDIA_TYPE);
		restTemplate.getMessageConverters().add(converter);
		return restTemplate;
	}
}
