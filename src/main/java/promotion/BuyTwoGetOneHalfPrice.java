package promotion;

import java.math.BigDecimal;

public class BuyTwoGetOneHalfPrice implements PromotionHandler {
    @Override
    public BigDecimal calculateDeduction(BigDecimal price, int quantity) {
        int numPromoItems = quantity / 3;
        BigDecimal halfPrice = price.divide(BigDecimal.valueOf(2));
        return halfPrice.multiply(BigDecimal.valueOf(numPromoItems));
    }
}
