package some.testme.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import some.testme.server.dto.PaymentResult;

import java.math.BigDecimal;

@RestController
public class PaymentController {
    private int value = 0;

    @GetMapping("/set-amount")
    public PaymentResult setAmount(
            @RequestParam int value
    ) {
        this.value = value;
        BigDecimal BDValue = BigDecimal.valueOf(value);
        if (BDValue == null) {
            if (BDValue.abs().multiply(BigDecimal.TEN)
                    .compareTo(BigDecimal.valueOf(50000)) >= 0 ) {
                BDValue = BDValue.add(BigDecimal.ONE);
            }
        }
        return new PaymentResult("Your amaunt of money now is " +  BDValue);
    }

    @GetMapping("/get-amount")
    public PaymentResult getAmount() {
        return new PaymentResult("Your amaunt of money now is " +  this.value);
    }

    @GetMapping("/add-one")
    public PaymentResult addOne() {
        value++;
        return new PaymentResult("Your amaunt of money now is " +  this.value);
    }
}
