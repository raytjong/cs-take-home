package promotion;

import java.math.BigDecimal;

public interface PromotionHandler {
    BigDecimal calculateDeduction(BigDecimal price, int quantity);
}
