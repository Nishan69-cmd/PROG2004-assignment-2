import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class ParkIO {

    private static final String SEPARATOR = ",";

    public static void savePark(Park park, String fileName) {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(fileName));
s
            HashSet<Integer> savedStaffIDs =
                    new HashSet<>();

            for (Attraction attraction : park.getAttractions()) {

                Staff operator = attraction.getOperator();

                if (operator != null
                        && !savedStaffIDs.contains(
                                operator.getStaffID())) {

                    writer.write(
                            "STAFF"
                                    + SEPARATOR
                                    + operator.getStaffID()
                                    + SEPARATOR
                                    + operator.getName()
                                    + SEPARATOR
                                    + operator.getAge()
                                    + SEPARATOR
                                    + operator.getRole());

                    writer.newLine();

                    savedStaffIDs.add(
                            operator.getStaffID());
                }
            }

          
            HashSet<Visitor> savedVisitors =
                    new HashSet<>();

            for (Attraction attraction : park.getAttractions()) {

                savedVisitors.addAll(
                        attraction.getWaitingVisitors());

                savedVisitors.addAll(
                        attraction.getVisitHistory());
            }

            for (Visitor visitor : savedVisitors) {

                writer.write(
                        "VISITOR"
                                + SEPARATOR
                                + visitor.getVisitorID()
                                + SEPARATOR
                                + visitor.getName()
                                + SEPARATOR
                                + visitor.getAge()
                                + SEPARATOR
                                + visitor.getTicketType());

                writer.newLine();
            }

            for (Attraction attraction : park.getAttractions()) {

                int operatorID = 0;

                if (attraction.getOperator() != null) {

                    operatorID =
                            attraction.getOperator()
                                    .getStaffID();
                }

                if (attraction instanceof Ride) {

                    writer.write(
                            "RIDE"
                                    + SEPARATOR
                                    + attraction.getAttractionID()
                                    + SEPARATOR
                                    + attraction.getName()
                                    + SEPARATOR
                                    + attraction.getCapacity()
                                    + SEPARATOR
                                    + operatorID);

                    writer.newLine();

                } else if (attraction instanceof Show) {

                    writer.write(
                            "SHOW"
                                    + SEPARATOR
                                    + attraction.getAttractionID()
                                    + SEPARATOR
                                    + attraction.getName()
                                    + SEPARATOR
                                    + attraction.getCapacity()
                                    + SEPARATOR
                                    + operatorID);

                    writer.newLine();
                }
            }

            for (Attraction attraction : park.getAttractions()) {

                for (Visitor visitor :
                        attraction.getWaitingVisitors()) {

                    writer.write(
                            "QUEUE"
                                    + SEPARATOR
                                    + attraction.getAttractionID()
                                    + SEPARATOR
                                    + visitor.getVisitorID());

                    writer.newLine();
                }
            }
            for (Attraction attraction : park.getAttractions()) {

                for (Visitor visitor :
                        attraction.getVisitHistory()) {

                        writer.write(
                                "HISTORY"
                                        + SEPARATOR
                                        + attraction.getAttractionID()
                                        + SEPARATOR
                                        + visitor.getVisitorID());

                        writer.newLine();
                }
                }
            writer.close();

            System.out.println(
                    "Park saved to " + fileName);

        } catch (IOException e) {

            System.out.println(
                    "Error saving park.");
        }
    }
}