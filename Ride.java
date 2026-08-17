/**
 * Represents a ride in the amusement park.
 * A Ride is both an Attraction and Inspectable.
 */
public class Ride
        extends Attraction
        implements Inspectable {

    private boolean closed;
    private String inspectionResult;

/**
 * Creates a new Ride.
 */
    public Ride(
            int attractionID,
            String name,
            int capacity) {

        super(
                attractionID,
                name,
                capacity);

        this.closed = false;
        this.inspectionResult =
                "Not inspected yet";
    }

/**
* Runs one ride cycle if all required
* conditions are satisfied.
*/
    @Override
    public void runCycle() {

// ride requires an operator
        if (getOperator() == null) {

            System.out.println(
                    getName()
                            + " cannot run: no operator.");

            return;
        }

// ride cannot operate while closed
        if (isClosed()) {

            System.out.println(
                    getName()
                            + " cannot run: ride is closed.");

            return;
        }

// ride requires at least one visitor
        if (!hasWaitingVisitors()) {

            System.out.println(
                    getName()
                            + " cannot run: no visitors waiting.");

            return;
        }

// serve visitors when all conditions are met
        serveVisitors();
    }

/**
 * Closes the ride for inspection.
 */
    @Override
    public void startInspection() {

        closed = true;

        System.out.println(
                getName()
                        + " is closed for inspection.");

        System.out.println(
                "Closed: " + closed);
    }

/**
 * Records the result and reopens the ride.
*/
    @Override
    public void finishInspection(
            String result) {

        inspectionResult = result;

        closed = false;

        System.out.println(
                getName()
                        + " inspection completed.");

        System.out.println(
                "Closed: " + closed);
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public String getInspectionResult() {
        return inspectionResult;
    }
}