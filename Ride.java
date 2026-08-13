public class Ride extends Attraction implements Inspectable {

    private boolean closed;
    private String inspectionResult;

    public Ride(int attractionID, String name, int capacity) {
        super(attractionID, name, capacity);

        this.closed = false;
        this.inspectionResult = "Not inspected yet";
    }

    @Override
    public void runCycle() {

        if (getOperator() == null) {
            System.out.println(
                    getName() + " cannot run: no operator.");
            return;
        }

        if (isClosed()) {
            System.out.println(
                    getName() + " cannot run: ride is closed.");
            return;
        }

        if (!hasWaitingVisitors()) {
            System.out.println(
                    getName() + " cannot run: no visitors waiting.");
            return;
        }

        serveVisitors();
    }

    @Override
    public void startInspection() {

        closed = true;

        System.out.println(
                getName() + " is closed for inspection.");

        System.out.println("Closed: " + closed);
    }

    @Override
    public void finishInspection(String result) {

        inspectionResult = result;

        closed = false;

        System.out.println(
                getName() + " inspection completed.");

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