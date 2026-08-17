import java.util.Comparator;

/**
 * Compares visitors by name first.
 * If the names are the same, ticket type is used.
 */
public class VisitorNameTicketComparator
        implements Comparator<Visitor> {

    @Override
    public int compare(
            Visitor visitor1,
            Visitor visitor2) {

// Compare the visitor names first
        int nameCompare =
                visitor1.getName()
                        .compareTo(
                                visitor2.getName());

// If names are different use that result
        if (nameCompare != 0) {
            return nameCompare;
        }

// If names are the same compare ticket types
        return visitor1.getTicketType()
                .compareTo(
                        visitor2.getTicketType());
    }
}
