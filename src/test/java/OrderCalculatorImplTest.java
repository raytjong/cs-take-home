import model.Flavour;
import model.OrderItem;
import model.OrderTotal;
import model.Promotion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCalculatorImplTest {
    private static OrderCalculatorImpl orderCalculator;

    private static OrderItem item1;
    private static OrderItem item2;
    private static OrderItem item3;

    @BeforeAll
    public static void setup() {
        orderCalculator = new OrderCalculatorImpl();

        Flavour rockyRoad = new Flavour("Rocky Road", BigDecimal.valueOf(8), Promotion.BUY_TWO_GET_ONE_FREE);
        Flavour cookiesAndCream = new Flavour("Cookies and Cream", BigDecimal.valueOf(10), Promotion.BUY_TWO_GET_ONE_HALF_PRICE);
        Flavour netflixAndChill = new Flavour("Netflix and Chill", BigDecimal.valueOf(12));

        item1 = new OrderItem(rockyRoad, 1);
        item2 = new OrderItem(cookiesAndCream, 3);
        item3 = new OrderItem(netflixAndChill, 2);
    }

    @Test
    public void testCalculateInvalidOrderTotal() {
        OrderTotal total1 = orderCalculator.calculateOrderTotal(null);
        assertEquals(BigDecimal.ZERO, total1.getOrderTotal());
        assertEquals(BigDecimal.ZERO, total1.getPromoTotal());
        assertEquals(BigDecimal.ZERO, total1.getAmountPayable());

        OrderTotal total2 = orderCalculator.calculateOrderTotal(List.of());
        assertEquals(BigDecimal.ZERO, total2.getOrderTotal());
        assertEquals(BigDecimal.ZERO, total2.getPromoTotal());
        assertEquals(BigDecimal.ZERO, total2.getAmountPayable());
    }

    @Test
    public void testCalculateValidOrderTotal() {
        List<OrderItem> order = List.of(item1, item2, item3);
        OrderTotal total = orderCalculator.calculateOrderTotal(order);
        assertEquals(BigDecimal.valueOf(62), total.getOrderTotal());
        assertEquals(BigDecimal.valueOf(5), total.getPromoTotal());
        assertEquals(BigDecimal.valueOf(57), total.getAmountPayable());
    }

    @Test
    public void testCalculateSingleOrder() {
        OrderTotal total = orderCalculator.calculateOrder(item2);
        assertEquals(BigDecimal.valueOf(30), total.getOrderTotal());
        assertEquals(BigDecimal.valueOf(5), total.getPromoTotal());
        assertEquals(BigDecimal.valueOf(25), total.getAmountPayable());
    }

    @Test
    public void testAggregateOrderTotal() {
        OrderTotal total1 = orderCalculator.calculateOrder(item1);
        OrderTotal total2 = orderCalculator.calculateOrder(item2);

        OrderTotal finalTotal = orderCalculator.aggregateOrderTotal(total1, total2);
        assertEquals(BigDecimal.valueOf(38), finalTotal.getOrderTotal());
        assertEquals(BigDecimal.valueOf(5), finalTotal.getPromoTotal());
        assertEquals(BigDecimal.valueOf(33), finalTotal.getAmountPayable());
    }
}
