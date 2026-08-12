import java.util.Comparator;

public class VisitorNameTicketComparator
        implements Comparator<Visitor> {

    @Override
    public int compare(Visitor visitor1, Visitor visitor2) {

        int nameCompare = visitor1.getName()
                .compareTo(visitor2.getName());

        if (nameCompare != 0) {
            return nameCompare;
        }

        return visitor1.getTicketType()
                .compareTo(visitor2.getTicketType());
    }
}