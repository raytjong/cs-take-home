import model.OrderTotal;
import promotion.BuyTwoGetOneFree;
import promotion.BuyTwoGetOneHalfPrice;
import promotion.PromotionHandler;
import model.Flavour;
import model.OrderItem;
import model.Promotion;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class OrderCalculatorImpl implements OrderCalculator {
    public static Map<Promotion, PromotionHandler> handlers = Map.of(
        Promotion.BUY_TWO_GET_ONE_FREE, new BuyTwoGetOneFree(),
        Promotion.BUY_TWO_GET_ONE_HALF_PRICE, new BuyTwoGetOneHalfPrice()
    );

    @Override
    public OrderTotal calculateOrderTotal(List<OrderItem> orderItemList) {
        if (orderItemList == null || orderItemList.isEmpty()) {
            return new OrderTotal();
        }

        return orderItemList
            .stream()
            .map(this::calculateOrder)
            .reduce(new OrderTotal(), this::aggregateOrderTotal);
    }

    OrderTotal calculateOrder(OrderItem orderItem) {
        Flavour flavour = orderItem.getFlavour();
        int quantity = orderItem.getQuantity();
        Promotion promo = flavour.getPromotion();
        BigDecimal price = flavour.getPrice();

        if (isInvalidOrder(price, quantity)) {
            return new OrderTotal();
        }

        BigDecimal orderTotal = price.multiply(BigDecimal.valueOf(quantity));
        BigDecimal promoTotal = promo == null ?
                BigDecimal.ZERO :
                handlers.get(promo).calculateDeduction(price, quantity);

        return new OrderTotal(
                orderTotal,
                promoTotal,
                orderTotal.subtract(promoTotal)
        );
    }

    OrderTotal aggregateOrderTotal(OrderTotal t1, OrderTotal t2) {
        return new OrderTotal(
                t1.getOrderTotal().add(t2.getOrderTotal()),
                t1.getPromoTotal().add(t2.getPromoTotal()),
                t1.getAmountPayable().add(t2.getAmountPayable())
        );
    }

    private boolean isInvalidOrder(BigDecimal price, int quantity) {
        return isInvalidPrice(price) || quantity <= 0;
    }

    private boolean isInvalidPrice(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) <= 0;
    }
}
