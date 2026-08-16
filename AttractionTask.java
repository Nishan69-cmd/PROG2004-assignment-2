public class AttractionTask implements Runnable {

    private Attraction attraction;
    private Park park;

    public AttractionTask(
            Attraction attraction,
            Park park) {

        this.attraction = attraction;
        this.park = park;
    }

    @Override
    public void run() {

        int visitsBefore =
                attraction.getVisitCount();

        attraction.runCycle();

        int visitsAfter =
                attraction.getVisitCount();

        int visitorsServed =
                visitsAfter - visitsBefore;

        park.addServedVisitors(
                visitorsServed);
    }
}