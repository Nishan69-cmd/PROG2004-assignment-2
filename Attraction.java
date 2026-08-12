import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
public abstract class Attraction {

    private int attractionID;
    private String name;
    private Staff operator;
    private Queue<Visitor> waitingLine;
    private ArrayList<Visitor> visitHistory;

  public Attraction(int attractionID, String name) {

    if (attractionID <= 0) {
        throw new IllegalArgumentException(
                "Attraction ID must be greater than 0");
    }

    Objects.requireNonNull(name, "Name must not be null");

    if (name.isBlank()) {
        throw new IllegalArgumentException(
                "Name must not be blank");
    }

    this.attractionID = attractionID;
    this.name = name;
    this.operator = null;
    this.waitingLine = new LinkedList<>();
    this.visitHistory = new ArrayList<>();

    }
    public int getAttractionID() {
    return attractionID;
    }

    public String getName() {
        return name;
    }

    public Staff getOperator() {
        return operator;
    }
    public void addVisitorToQueue(Visitor visitor) {

    Objects.requireNonNull(
            visitor,
            "Visitor must not be null");

    waitingLine.add(visitor);

    System.out.println(visitor.getName()
            + " joined the waiting line for "
            + name);
    }
    public Visitor removeNextVisitor() {

    Visitor visitor = waitingLine.poll();

    if (visitor == null) {
        System.out.println(
                "No visitors waiting for " + name);
    } else {
        System.out.println(visitor.getName()
                + " removed from the waiting line for "
                + name);
    }

    return visitor;
    }
    public void displayWaitingLine() {

        System.out.println("Waiting line for " + name);

        if (waitingLine.isEmpty()) {
            System.out.println("No visitors waiting.");
        } 
        else {

            Iterator<Visitor> iterator = waitingLine.iterator();

            while (iterator.hasNext()) {
                Visitor visitor = iterator.next();

                System.out.println(visitor);
                System.out.println();
            }
        }
    }
    public void recordVisit(Visitor visitor) {

        Objects.requireNonNull(
                visitor,
                "Visitor must not be null");

        visitHistory.add(visitor);

        System.out.println(visitor.getName()
                + " added to visit history for "
                + name);
    }
    public void displayVisitHistory() {

        System.out.println("Visit history for " + name);

        if (visitHistory.isEmpty()) {
            System.out.println("No visitors in history.");
        } else {

            Iterator<Visitor> iterator = visitHistory.iterator();

            while (iterator.hasNext()) {
                Visitor visitor = iterator.next();

                System.out.println(visitor);
                System.out.println();
            }
        }
    }
    public int getVisitCount() {
    return visitHistory.size();
    }

    public boolean hasVisited(Visitor visitor) {
        return visitHistory.contains(visitor);
    }
    public void displayHistoryByAge() {

        ArrayList<Visitor> sortedHistory = new ArrayList<>();

        sortedHistory.addAll(visitHistory);

        Collections.sort(sortedHistory);

        System.out.println("Visit history sorted by age for " + name);

        for (Visitor visitor : sortedHistory) {
            System.out.println(visitor);
            System.out.println();
        }
    }
    public void displayHistoryByNameAndTicket() {

    ArrayList<Visitor> sortedHistory = new ArrayList<>();

    sortedHistory.addAll(visitHistory);

    Collections.sort(
            sortedHistory,
            new VisitorNameTicketComparator());

    System.out.println(
            "Visit history sorted by name and ticket type for "
                    + name);

    for (Visitor visitor : sortedHistory) {
        System.out.println(visitor);
        System.out.println();
    }
    }
    public void assignOperator(Staff operator) {

    Objects.requireNonNull(operator, "Operator must not be null");

    this.operator = operator;

    System.out.println(operator.getName()
            + " assigned to " + name);
    }

    public void removeOperator() {

        if (operator == null) {
            System.out.println("No operator assigned to " + name);
        } else {
            System.out.println(operator.getName()
                    + " removed from " + name);

            operator = null;
        }
    }
}

