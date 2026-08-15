import java.util.HashMap;
import java.util.HashSet;
public class Park {

    private HashMap<Integer, Attraction> attractions;

    public Park() {
        attractions = new HashMap<>();
    }

    public void registerAttraction(Attraction attraction) {

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

    public Attraction findAttraction(int attractionID) {

        Attraction attraction =
                attractions.get(attractionID);

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

    public void displayAttractionVisitorCounts() {

            System.out.println("Visitors served by each attraction");

            for (Attraction attraction : attractions.values()) {

                System.out.println(
                        attraction.getName()
                                + ": "
                                + attraction.getVisitCount());
            }
    }
    public int getDistinctVisitorCount() {

        HashSet<Visitor> distinctVisitors = new HashSet<>();

        for (Attraction attraction : attractions.values()) {

            distinctVisitors.addAll(
                    attraction.getVisitHistory());
        }

        System.out.println(
                "Distinct visitors in the park: "
                        + distinctVisitors.size());

        return distinctVisitors.size();
    }
    
}