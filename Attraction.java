import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Abstract parent class for park attractions.
 * Ride and Show inherit from this class.
 */
public abstract class Attraction {

    private int attractionID;
    private String name;
    private Staff operator;

// FIFO waiting line
    private Queue<Visitor> waitingLine;

// Stores visitors who have already been served
    private ArrayList<Visitor> visitHistory;

    private int capacity;
    private int cycleCount;

/**
* Creates a new attraction.
 */
    public Attraction(
            int attractionID,
            String name,
            int capacity) {

// Validate attraction ID
        if (attractionID <= 0) {
            throw new IllegalArgumentException(
                    "Attraction ID must be greater than 0");
        }

// Validate attraction name
        Objects.requireNonNull(
                name,
                "Name must not be null");

        if (name.isBlank()) {
            throw new IllegalArgumentException(
                    "Name must not be blank");
        }

// validate capacity
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be greater than 0");
        }

        this.attractionID = attractionID;
        this.name = name;
        this.operator = null;

// LinkelList is used as a FIFO Queue
        this.waitingLine =
                new LinkedList<>();

        this.visitHistory =
                new ArrayList<>();

        this.capacity = capacity;
        this.cycleCount = 0;
    }

// Getter methods
    public int getAttractionID() {
        return attractionID;
    }

    public String getName() {
        return name;
    }

    public Staff getOperator() {
        return operator;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCycleCount() {
        return cycleCount;
    }

/**
* Each subclass provides its own rules
* for running an attraction cycle.
*/
    public abstract void runCycle();

 /**
 * Adds a visitor to the end of the waiting line.
 */
    public void addVisitorToQueue(
            Visitor visitor) {

        Objects.requireNonNull(
                visitor,
                "Visitor must not be null");

        waitingLine.add(visitor);

        System.out.println(
                visitor.getName()
                        + " joined the waiting line for "
                        + name);
    }

/**
* Removes the visitor who has waited longest.
*/
    public Visitor removeNextVisitor() {

// poll() removes the first visitor in FIFO order
        Visitor visitor =
                waitingLine.poll();

        if (visitor == null) {

            System.out.println(
                    "No visitors waiting for "
                            + name);

        } else {

            System.out.println(
                    visitor.getName()
                            + " removed from the waiting line for "
                            + name);
        }

        return visitor;
    }

/**
 * Displays all visitors currently waiting.
 */
    public void displayWaitingLine() {

        System.out.println(
                "Waiting line for " + name);

        if (waitingLine.isEmpty()) {

            System.out.println(
                    "No visitors waiting.");

        } else {

// iterator is used to move through the queue
            Iterator<Visitor> iterator =
                    waitingLine.iterator();

            while (iterator.hasNext()) {

                Visitor visitor =
                        iterator.next();

                System.out.println(visitor);
                System.out.println();
            }
        }
    }

 /**
 * Adds a visitor to the visit history.
 */
    public void recordVisit(
            Visitor visitor) {

        Objects.requireNonNull(
                visitor,
                "Visitor must not be null");

        visitHistory.add(visitor);

        System.out.println(
                visitor.getName()
                        + " added to visit history for "
                        + name);
    }

 /**
 * Displays every visit recorded for this attraction.
 */
    public void displayVisitHistory() {

        System.out.println(
                "Visit history for " + name);

        if (visitHistory.isEmpty()) {

            System.out.println(
                    "No visitors in history.");

        } else {

            Iterator<Visitor> iterator =
                    visitHistory.iterator();

            while (iterator.hasNext()) {

                Visitor visitor =
                        iterator.next();

                System.out.println(visitor);
                System.out.println();
            }
        }
    }

/**
 * Returns the total number of recorded visits.
 */
    public int getVisitCount() {
        return visitHistory.size();
    }

/**
 * Returns a copy of the visit history.
 * This prevents other classes from directly
* changing the original list.
 */
    public ArrayList<Visitor> getVisitHistory() {

        ArrayList<Visitor> historyCopy =
                new ArrayList<>();

        historyCopy.addAll(
                visitHistory);

        return historyCopy;
    }

/**
 * Returns a copy of the current waiting line.
*/
    public ArrayList<Visitor> getWaitingVisitors() {

        ArrayList<Visitor> waitingVisitors =
                new ArrayList<>();

        waitingVisitors.addAll(
                waitingLine);

        return waitingVisitors;
    }

/**
 * Checks whether a visitor appears in
 * the visit history.
*/
    public boolean hasVisited(
            Visitor visitor) {

        return visitHistory.contains(
                visitor);
    }

/**
* Displays visit history using Visitor's
* natural ordering by age.
*/
    public void displayHistoryByAge() {

        ArrayList<Visitor> sortedHistory =
                new ArrayList<>();

        sortedHistory.addAll(
                visitHistory);

        Collections.sort(
                sortedHistory);

        System.out.println(
                "Visit history sorted by age for "
                        + name);

        for (Visitor visitor :
                sortedHistory) {

            System.out.println(visitor);
            System.out.println();
        }
    }

/**
 * Displays history sorted by visitor name
 * and ticket type.
 */
    public void displayHistoryByNameAndTicket() {

        ArrayList<Visitor> sortedHistory =
                new ArrayList<>();

        sortedHistory.addAll(
                visitHistory);

        Collections.sort(
                sortedHistory,
                new VisitorNameTicketComparator());

        System.out.println(
                "Visit history sorted by name and ticket type for "
                        + name);

        for (Visitor visitor :
                sortedHistory) {

            System.out.println(visitor);
            System.out.println();
        }
    }

/**
 * Used by Ride to check whether someone
 * is waiting.
*/
    protected boolean hasWaitingVisitors() {
        return !waitingLine.isEmpty();
    }

/**
 * Serves up to the attraction capacity.
 * Served visitors move from the queue
 * into the visit history.
 */
    protected void serveVisitors() {

        int served = 0;

        while (served < capacity
                && !waitingLine.isEmpty()) {

            Visitor visitor =
                    waitingLine.poll();

            visitHistory.add(
                    visitor);

            System.out.println(
                    visitor.getName()
                            + " served by "
                            + name);

            served++;
        }

// one completed operation counts as one cycle
        cycleCount++;

        System.out.println(
                name
                        + " completed cycle "
                        + cycleCount);
    }

/**
 * Assigns a staff member as the operator.
*/
    public void assignOperator(
            Staff operator) {

        Objects.requireNonNull(
                operator,
                "Operator must not be null");

        this.operator = operator;

        System.out.println(
                operator.getName()
                        + " assigned to "
                        + name);
    }

/**
 * Removes the current attraction operator.
*/
    public void removeOperator() {

        if (operator == null) {

            System.out.println(
                    "No operator assigned to "
                            + name);

        } else {

            System.out.println(
                    operator.getName()
                            + " removed from "
                            + name);

            operator = null;
        }
    }
}