import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Manages the attractions in the amusement park.
 */
public class Park {

// attraction ID is used as the HashMap key
    private HashMap<Integer, Attraction> attractions;

// shared counter used during concurrency
    private int totalVisitorsServed;

/**
* Creates an empty park.
*/
    public Park() {

        attractions =
                new HashMap<>();

        totalVisitorsServed = 0;
    }

/**
* Registers an attraction in the park.
*/
    public void registerAttraction(
            Attraction attraction) {

        if (attraction == null) {

            System.out.println(
                    "Cannot register null attraction.");

            return;
        }

        attractions.put(
                attraction.getAttractionID(),
                attraction);

        System.out.println(
                attraction.getName()
                        + " registered in the park.");
    }

/**
* Finds an attraction directly using its ID.
*/
    public Attraction findAttraction(
            int attractionID) {

        Attraction attraction =
                attractions.get(
                        attractionID);

        if (attraction == null) {

            System.out.println(
                    "No attraction found with ID "
                            + attractionID);

        } else {

            System.out.println(
                    attraction.getName()
                            + " found.");
        }

        return attraction;
    }

/**
* Returns all registered attractions as
* an ArrayList.
*/
    public ArrayList<Attraction> getAttractions() {

        ArrayList<Attraction> attractionList =
                new ArrayList<>();

        attractionList.addAll(
                attractions.values());

        return attractionList;
    }

/**
* Displays the number of visits served by
* every attraction.
*/
    public void displayAttractionVisitorCounts() {

        System.out.println(
                "Visitors served by each attraction");

        for (Attraction attraction :
                attractions.values()) {

            System.out.println(
                    attraction.getName()
                            + ": "
                            + attraction.getVisitCount());
        }
    }

/**
* Counts unique visitors across all attractions.
* HashSet prevents duplicate visitors.
*/
    public int getDistinctVisitorCount() {

        HashSet<Visitor> distinctVisitors =
                new HashSet<>();

        for (Attraction attraction :
                attractions.values()) {

            distinctVisitors.addAll(
                    attraction.getVisitHistory());
        }

        System.out.println(
                "Distinct visitors in the park: "
                        + distinctVisitors.size());

        return distinctVisitors.size();
    }
/**
 * Safely adds to the shared visitor count.
 * synchronized prevents multiple threads
* from updating the number at the same time.
*/
    public synchronized void addServedVisitors(
            int number) {

        totalVisitorsServed =
                totalVisitorsServed + number;
    }

/**
* Returns the shared concurrent visitor count.
*/
    public int getTotalVisitorsServed() {
        return totalVisitorsServed;
    }
}