
package some.testme.server.dto.integration.rates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRatesResponse {

	private String base;
	private String date;
	private String disclaimer;
	private Rates rates;
	private Long timestamp;

}
