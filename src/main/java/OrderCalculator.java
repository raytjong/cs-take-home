import model.OrderItem;
import model.OrderTotal;

import java.util.List;

public interface OrderCalculator {
    OrderTotal calculateOrderTotal(List<OrderItem> orderItemList);
}
