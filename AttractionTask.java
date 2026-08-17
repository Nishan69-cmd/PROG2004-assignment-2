/**
 * Runnable task used to operate an attraction
 * from a separate thread.
 */
public class AttractionTask
        implements Runnable {

    private Attraction attraction;
    private Park park;

/**
* Creates a task for one attraction.
*/
    public AttractionTask(
            Attraction attraction,
            Park park) {

        this.attraction = attraction;
        this.park = park;
    }

/**
* Runs one attraction cycle and updates
* the shared park-wide visitor count.
*/
    @Override
    public void run() {

// record history size before the cycle
        int visitsBefore =
                attraction.getVisitCount();

// run the attraction
        attraction.runCycle();

// record history size after the cycle
        int visitsAfter =
                attraction.getVisitCount();

// difference tells us how many were served
        int visitorsServed =
                visitsAfter - visitsBefore;

// safely update shared park counter
        park.addServedVisitors(
                visitorsServed);
    }
}