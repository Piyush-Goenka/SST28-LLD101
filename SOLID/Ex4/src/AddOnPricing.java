import java.util.*;

public class AddOnPricing implements PricingComponent {
    private final Map<AddOn, Double> prices = new HashMap<>();

    public AddOnPricing register(AddOn addOn, double price) {
        prices.put(addOn, price);
        return this;
    }

    public Money monthlyFee(BookingRequest req) {
        double total = 0.0;
        for (AddOn a : req.addOns) {
            total += prices.getOrDefault(a, 0.0);
        }
        return new Money(total);
    }
}
