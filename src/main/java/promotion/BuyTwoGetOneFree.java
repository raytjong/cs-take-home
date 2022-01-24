package promotion;

import java.math.BigDecimal;

public class BuyTwoGetOneFree implements PromotionHandler {
    @Override
    public BigDecimal calculateDeduction(BigDecimal price, int quantity) {
        int numItemsFree = quantity / 3;
        return price.multiply(BigDecimal.valueOf(numItemsFree));
    }
}
