import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class ParkIO {

    private static final String SEPARATOR = ",";

    public static void savePark(Park park, String fileName) {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(fileName));

            // Save operators
            HashSet<Integer> savedStaffIDs =
                    new HashSet<>();

            for (Attraction attraction : park.getAttractions()) {

                Staff operator =
                        attraction.getOperator();

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

            // Save visitors
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

            // Save attractions
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

            // Save waiting queues
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

            // Save visit histories
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

    public static void readBackupFile(String fileName) {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(fileName));

            String line =
                    reader.readLine();

            while (line != null) {

                System.out.println(line);

                line =
                        reader.readLine();
            }

            reader.close();

        } catch (IOException e) {

            System.out.println(
                    "Error reading park backup.");
        }
    }

    public static Park restorePark(String fileName) {

        Park restoredPark =
                new Park();

        HashMap<Integer, Staff> staff =
                new HashMap<>();

        HashMap<Integer, Visitor> visitors =
                new HashMap<>();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(fileName));

            String line =
                    reader.readLine();

            while (line != null) {

                String[] parts =
                        line.split(SEPARATOR, -1);

                // Restore staff
                if (parts[0].equals("STAFF")) {

                    int staffID =
                            Integer.parseInt(parts[1]);

                    String name =
                            parts[2];

                    int age =
                            Integer.parseInt(parts[3]);

                    String role =
                            parts[4];

                    Staff restoredStaff =
                            new Staff(
                                    staffID,
                                    name,
                                    age,
                                    role);

                    staff.put(
                            staffID,
                            restoredStaff);

                // Restore visitors
                } else if (parts[0].equals("VISITOR")) {

                    int visitorID =
                            Integer.parseInt(parts[1]);

                    String name =
                            parts[2];

                    int age =
                            Integer.parseInt(parts[3]);

                    String ticketType =
                            parts[4];

                    Visitor restoredVisitor =
                            new Visitor(
                                    visitorID,
                                    name,
                                    age,
                                    ticketType);

                    visitors.put(
                            visitorID,
                            restoredVisitor);

                // Restore rides
                } else if (parts[0].equals("RIDE")) {

                    int attractionID =
                            Integer.parseInt(parts[1]);

                    String name =
                            parts[2];

                    int capacity =
                            Integer.parseInt(parts[3]);

                    int operatorID =
                            Integer.parseInt(parts[4]);

                    Ride restoredRide =
                            new Ride(
                                    attractionID,
                                    name,
                                    capacity);

                    if (operatorID != 0) {

                        Staff operator =
                                staff.get(operatorID);

                        if (operator == null) {
                            throw new IllegalArgumentException(
                                    "Operator not found.");
                        }

                        restoredRide.assignOperator(
                                operator);
                    }

                    restoredPark.registerAttraction(
                            restoredRide);

                // Restore shows
                } else if (parts[0].equals("SHOW")) {

                    int attractionID =
                            Integer.parseInt(parts[1]);

                    String name =
                            parts[2];

                    int capacity =
                            Integer.parseInt(parts[3]);

                    int operatorID =
                            Integer.parseInt(parts[4]);

                    Show restoredShow =
                            new Show(
                                    attractionID,
                                    name,
                                    capacity);

                    if (operatorID != 0) {

                        Staff operator =
                                staff.get(operatorID);

                        if (operator == null) {
                            throw new IllegalArgumentException(
                                    "Operator not found.");
                        }

                        restoredShow.assignOperator(
                                operator);
                    }

                    restoredPark.registerAttraction(
                            restoredShow);

                // Restore waiting queues
                } else if (parts[0].equals("QUEUE")) {

                    int attractionID =
                            Integer.parseInt(parts[1]);

                    int visitorID =
                            Integer.parseInt(parts[2]);

                    Attraction attraction =
                            restoredPark.findAttraction(
                                    attractionID);

                    Visitor visitor =
                            visitors.get(visitorID);

                    if (attraction == null
                            || visitor == null) {

                        throw new IllegalArgumentException(
                                "Invalid queue record.");
                    }

                    attraction.addVisitorToQueue(
                            visitor);

                // Restore visit histories
                } else if (parts[0].equals("HISTORY")) {

                    int attractionID =
                            Integer.parseInt(parts[1]);

                    int visitorID =
                            Integer.parseInt(parts[2]);

                    Attraction attraction =
                            restoredPark.findAttraction(
                                    attractionID);

                    Visitor visitor =
                            visitors.get(visitorID);

                    if (attraction == null
                            || visitor == null) {

                        throw new IllegalArgumentException(
                                "Invalid history record.");
                    }

                    attraction.recordVisit(
                            visitor);

                } else {

                    throw new IllegalArgumentException(
                            "Unknown record in backup file.");
                }

                line =
                        reader.readLine();
            }

            reader.close();

            System.out.println(
                    "Park restored from "
                            + fileName);

        } catch (IOException e) {

            System.out.println(
                    "Error restoring park.");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid data in backup file.");

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println(
                    "Corrupt backup file.");
        }

        return restoredPark;
    }
}