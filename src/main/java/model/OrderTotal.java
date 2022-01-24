package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class OrderTotal {
    private final BigDecimal orderTotal;
    private final BigDecimal promoTotal;
    private final BigDecimal amountPayable;

    public OrderTotal() {
        orderTotal = BigDecimal.ZERO;
        promoTotal = BigDecimal.ZERO;
        amountPayable = BigDecimal.ZERO;
    }
}
