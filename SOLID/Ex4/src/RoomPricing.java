import java.util.*;

public class RoomPricing implements PricingComponent {
    private final Map<Integer, Double> prices = new HashMap<>();

    public RoomPricing register(int roomType, double basePrice) {
        prices.put(roomType, basePrice);
        return this;
    }

    public Money monthlyFee(BookingRequest req) {
        double base = prices.getOrDefault(req.roomType, 16000.0);
        return new Money(base);
    }
}
