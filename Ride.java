public class Ride extends Attraction implements Inspectable {

    private boolean closed;
    private String inspectionResult;

    public Ride(int attractionID, String name) {
        super(attractionID, name);

        this.closed = false;
        this.inspectionResult = "Not inspected yet";
    }

    @Override
    public void inspect(String result) {

        closed = true;

        System.out.println(getName()
                + " is closed for inspection.");

        System.out.println("Closed: " + closed);

        inspectionResult = result;

        closed = false;

        System.out.println(getName()
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