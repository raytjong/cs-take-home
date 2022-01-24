package promotion;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionHandlerTest {
    private static PromotionHandler buyTwoGetOneFree;
    private static PromotionHandler buyTwoGetOneHalfPrice;

    @BeforeAll
    public static void setup() {
        buyTwoGetOneFree = new BuyTwoGetOneFree();
        buyTwoGetOneHalfPrice = new BuyTwoGetOneHalfPrice();
    }

    @Test
    public void testBuyTwoGetOneFree() {
        BigDecimal promo = buyTwoGetOneFree.calculateDeduction(BigDecimal.TEN, 3);
        assertEquals(BigDecimal.TEN, promo);
    }

    @Test
    public void testBuyTwoGetOneFreeNotApplied() {
        BigDecimal promo = buyTwoGetOneFree.calculateDeduction(BigDecimal.TEN, 1);
        assertEquals(BigDecimal.ZERO, promo);
    }

    @Test
    public void testBuyTwoGetOneHalfPrice() {
        BigDecimal promo = buyTwoGetOneHalfPrice.calculateDeduction(BigDecimal.TEN, 3);
        assertEquals(BigDecimal.valueOf(5), promo);
    }

    @Test
    public void testBuyTwoGetOneHalfPriceNotApplied() {
        BigDecimal promo = buyTwoGetOneFree.calculateDeduction(BigDecimal.TEN, 2);
        assertEquals(BigDecimal.ZERO, promo);
    }
}
