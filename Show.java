/**
 * Represents a show in the amusement park.
 */
public class Show extends Attraction {

/**
 * Creates a new Show.
*/
    public Show(
            int attractionID,
            String name,
            int capacity) {

        super(
                attractionID,
                name,
                capacity);
    }

/**
* Runs one show cycle.
* A show requires an operator but may run
* even when there are no visitors waiting.
*/
    @Override
    public void runCycle() {

// show cannot run without an operator
        if (getOperator() == null) {

            System.out.println(
                    getName()
                            + " cannot run: no operator.");

            return;
        }

// unlike a ride a show may run empty
        serveVisitors();
    }
}