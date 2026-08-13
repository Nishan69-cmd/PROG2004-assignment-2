public class Show extends Attraction {

    public Show(int attractionID, String name, int capacity) {
        super(attractionID, name, capacity);
    }

    @Override
    public void runCycle() {

        if (getOperator() == null) {
            System.out.println(
                    getName() + " cannot run: no operator.");
            return;
        }

        serveVisitors();
    }
}