package some.testme.server.service;

import some.testme.server.dto.ApiResult;

public interface PaymentService {
	ApiResult setAmount(
			String name,
			Integer value
	);

	ApiResult getAmount(String name);

	ApiResult addOne(String name);
}
