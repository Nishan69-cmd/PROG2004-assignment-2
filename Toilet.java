import java.util.Objects;

public class Toilet implements Inspectable {

    private int toiletID;
    private String location;
    private boolean closed;
    private String inspectionResult;

    public Toilet(int toiletID, String location) {

        if (toiletID <= 0) {
            throw new IllegalArgumentException(
                    "Toilet ID must be greater than 0");
        }

        Objects.requireNonNull(
                location,
                "Location must not be null");

        if (location.isBlank()) {
            throw new IllegalArgumentException(
                    "Location must not be blank");
        }

        this.toiletID = toiletID;
        this.location = location;
        this.closed = false;
        this.inspectionResult = "Not inspected yet";
    }

    public int getToiletID() {
        return toiletID;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public void inspect(String result) {

        closed = true;

        System.out.println("Toilet at "
                + location
                + " is closed for inspection.");

        System.out.println("Closed: " + closed);

        inspectionResult = result;

        closed = false;

        System.out.println("Toilet at "
                + location
                + " inspection completed.");

        System.out.println("Closed: " + closed);
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