import java.util.*;

public class PricingService {
    private final Map<String, MenuItem> menu;

    public PricingService(Map<String, MenuItem> menu) { this.menu = menu; }

    public double lineTotal(OrderLine line) {
        MenuItem item = menu.get(line.itemId);
        return item.price * line.qty;
    }

    public double subtotal(List<OrderLine> lines) {
        double sum = 0.0;
        for (OrderLine l : lines) {
            sum += lineTotal(l);
        }
        return sum;
    }

    public MenuItem getItem(String itemId) {
        return menu.get(itemId);
    }
}
