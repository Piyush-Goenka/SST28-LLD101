package parkingLot;
public interface PricingStrategy {
    double calculateFee(Ticket ticket, long exitTimeMillis);
}