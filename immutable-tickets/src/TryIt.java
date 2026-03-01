import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // Service operations create NEW immutable instances.
        IncidentTicket assigned = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nOriginal (unchanged): " + t);
        System.out.println("Assigned copy: " + assigned);
        System.out.println("Escalated copy: " + escalated);

        // Demonstrate that external tag mutations are blocked.
        List<String> tags = escalated.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException ex) {
            System.out.println("\nExternal tag mutation blocked: " + ex.getClass().getSimpleName());
        }
        System.out.println("After external tag mutation attempt: " + escalated);

        // Direct setter mutations no longer compile because setters were removed.
    }
}
