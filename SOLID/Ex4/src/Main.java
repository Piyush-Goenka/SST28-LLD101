import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        RoomPricing roomPricing = new RoomPricing()
                .register(LegacyRoomTypes.SINGLE, 14000.0)
                .register(LegacyRoomTypes.DOUBLE, 15000.0)
                .register(LegacyRoomTypes.TRIPLE, 12000.0);

        AddOnPricing addOnPricing = new AddOnPricing()
                .register(AddOn.MESS, 1000.0)
                .register(AddOn.LAUNDRY, 500.0)
                .register(AddOn.GYM, 300.0);

        List<PricingComponent> components = List.of(roomPricing, addOnPricing);

        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        HostelFeeCalculator calc = new HostelFeeCalculator(components, new FakeBookingRepo());
        calc.process(req);
    }
}
