import java.util.Objects;

/**
 * Represents a toilet in the park.
 * A toilet can be inspected by staff.
 */
public class Toilet implements Inspectable {

    private int toiletID;
    private String location;

// inspection information
    private boolean closed;
    private String inspectionResult;

/**
 * Creates a new Toilet object.
*/
    public Toilet(
            int toiletID,
            String location) {

// validate toilet ID
        if (toiletID <= 0) {
            throw new IllegalArgumentException(
                    "Toilet ID must be greater than 0");
        }

// validate location
        Objects.requireNonNull(
                location,
                "Location must not be null");

        if (location.isBlank()) {
            throw new IllegalArgumentException(
                    "Location must not be blank");
        }

        this.toiletID = toiletID;
        this.location = location;

// toilet begins open
        this.closed = false;
        this.inspectionResult =
                "Not inspected yet";
    }

// getter methods
    public int getToiletID() {
        return toiletID;
    }

    public String getLocation() {
        return location;
    }

/**
* Closes the toilet while it is inspected.
*/
    @Override
    public void startInspection() {

        closed = true;

        System.out.println(
                "Toilet at "
                        + location
                        + " is closed for inspection.");

        System.out.println(
                "Closed: " + closed);
    }

/**
* Saves the inspection result and reopens
* the toilet.
*/
    @Override
    public void finishInspection(String result) {

        inspectionResult = result;

        closed = false;

        System.out.println(
                "Toilet at "
                        + location
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