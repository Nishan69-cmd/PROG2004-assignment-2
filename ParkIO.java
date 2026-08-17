import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Handles saving and restoring park information
 * using a simple text file.
 */
public class ParkIO {

// separator used between fields in the text file
    private static final String SEPARATOR = ",";

/**
* Saves the park state into a text file.
*/
    public static void savePark(
            Park park,
            String fileName) {

      try (BufferedWriter writer =
        new BufferedWriter(
                new FileWriter(fileName))) {
// save staff/operators
           
            HashSet<Integer> savedStaffIDs =
                    new HashSet<>();

            for (Attraction attraction :
                    park.getAttractions()) {

                Staff operator =
                        attraction.getOperator();

// save each staff member only once
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

// save visitors
           
            HashSet<Visitor> savedVisitors =
                    new HashSet<>();

            for (Attraction attraction :
                    park.getAttractions()) {

                savedVisitors.addAll(
                        attraction.getWaitingVisitors());

                savedVisitors.addAll(
                        attraction.getVisitHistory());
            }

// hashSet prevents duplicate visitor records
            for (Visitor visitor :
                    savedVisitors) {

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

// save attractions

            for (Attraction attraction :
                    park.getAttractions()) {

// 0 means there is no operator assigned
                int operatorID = 0;

                if (attraction.getOperator()
                        != null) {

                    operatorID =
                            attraction
                                    .getOperator()
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

                } else if (attraction
                        instanceof Show) {

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

// save waiting queues
            
            for (Attraction attraction :
                    park.getAttractions()) {

                for (Visitor visitor :
                        attraction.getWaitingVisitors()) {

// save attraction ID and visitor ID
                    writer.write(
                            "QUEUE"
                                    + SEPARATOR
                                    + attraction.getAttractionID()
                                    + SEPARATOR
                                    + visitor.getVisitorID());

                    writer.newLine();
                }
            }

// save visit histories
            
            for (Attraction attraction :
                    park.getAttractions()) {

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


            System.out.println(
                    "Park saved to "
                            + fileName);

        } catch (IOException e) {

            System.out.println(
                    "Error saving park.");
        }
    }

/**
* Reads and displays the backup file.
* This method is mainly used to test the file.
*/
    public static void readBackupFile(
            String fileName) {

      try (BufferedReader reader =
        new BufferedReader(
                new FileReader(fileName))) {
            String line =
                    reader.readLine();

            while (line != null) {

                System.out.println(line);

                line =
                        reader.readLine();
            }


        } catch (IOException e) {

            System.out.println(
                    "Error reading park backup.");
        }
    }

/**
* Restores a fresh Park object from
* a backup text file.
*/
    public static Park restorePark(
            String fileName) {

        Park restoredPark =
                new Park();

// temporary maps allow IDs in the file
// to be connected back to real objects
        HashMap<Integer, Staff> staff =
                new HashMap<>();

        HashMap<Integer, Visitor> visitors =
                new HashMap<>();

        try (BufferedReader reader =
        new BufferedReader(
                new FileReader(fileName))) {

            String line =
                    reader.readLine();

            while (line != null) {

                // -1 keeps empty fields if they exist
                String[] parts =
                        line.split(
                                SEPARATOR,
                                -1);

// restore staff
                
                if (parts[0].equals("STAFF")) {

                    int staffID =
                            Integer.parseInt(
                                    parts[1]);

                    String name =
                            parts[2];

                    int age =
                            Integer.parseInt(
                                    parts[3]);

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

// restore visitors
               
                } else if (parts[0]
                        .equals("VISITOR")) {

                    int visitorID =
                            Integer.parseInt(
                                    parts[1]);

                    String name =
                            parts[2];

                    int age =
                            Integer.parseInt(
                                    parts[3]);

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

// restore rides
                
                } else if (parts[0]
                        .equals("RIDE")) {

                    int attractionID =
                            Integer.parseInt(
                                    parts[1]);

                    String name =
                            parts[2];

                    int capacity =
                            Integer.parseInt(
                                    parts[3]);

                    int operatorID =
                            Integer.parseInt(
                                    parts[4]);

                    Ride restoredRide =
                            new Ride(
                                    attractionID,
                                    name,
                                    capacity);

// restore operator if one existed
                    if (operatorID != 0) {

                        Staff operator =
                                staff.get(
                                        operatorID);

                        if (operator == null) {

                            throw new IllegalArgumentException(
                                    "Operator not found.");
                        }

                        restoredRide.assignOperator(
                                operator);
                    }

                    restoredPark.registerAttraction(
                            restoredRide);

// testore shows
                
                } else if (parts[0]
                        .equals("SHOW")) {

                    int attractionID =
                            Integer.parseInt(
                                    parts[1]);

                    String name =
                            parts[2];

                    int capacity =
                            Integer.parseInt(
                                    parts[3]);

                    int operatorID =
                            Integer.parseInt(
                                    parts[4]);

                    Show restoredShow =
                            new Show(
                                    attractionID,
                                    name,
                                    capacity);

                    if (operatorID != 0) {

                        Staff operator =
                                staff.get(
                                        operatorID);

                        if (operator == null) {

                            throw new IllegalArgumentException(
                                    "Operator not found.");
                        }

                        restoredShow.assignOperator(
                                operator);
                    }

                    restoredPark.registerAttraction(
                            restoredShow);

// restore waiting queues
                
                } else if (parts[0]
                        .equals("QUEUE")) {

                    int attractionID =
                            Integer.parseInt(
                                    parts[1]);

                    int visitorID =
                            Integer.parseInt(
                                    parts[2]);

                    Attraction attraction =
                            restoredPark.findAttraction(
                                    attractionID);

                    Visitor visitor =
                            visitors.get(
                                    visitorID);

                    if (attraction == null
                            || visitor == null) {

                        throw new IllegalArgumentException(
                                "Invalid queue record.");
                    }

                    attraction.addVisitorToQueue(
                            visitor);

// restore visit histories

                } else if (parts[0]
                        .equals("HISTORY")) {

                    int attractionID =
                            Integer.parseInt(
                                    parts[1]);

                    int visitorID =
                            Integer.parseInt(
                                    parts[2]);

                    Attraction attraction =
                            restoredPark.findAttraction(
                                    attractionID);

                    Visitor visitor =
                            visitors.get(
                                    visitorID);

                    if (attraction == null
                            || visitor == null) {

                        throw new IllegalArgumentException(
                                "Invalid history record.");
                    }

                    attraction.recordVisit(
                            visitor);

                } else {

// unknown record means the file is invalid
                    throw new IllegalArgumentException(
                            "Unknown record in backup file.");
                }

                line =
                        reader.readLine();
            }
            
            System.out.println(
                    "Park restored from "
                            + fileName);

        } catch (IOException e) {

// handles missing or unreadable file
            System.out.println(
                    "Error restoring park.");

        } catch (IllegalArgumentException e) {

// handles invalid values such as ABC instead of ID
            System.out.println(
                    "Invalid data in backup file.");

        } catch (ArrayIndexOutOfBoundsException e) {

// handles records with missing fields
            System.out.println(
                    "Corrupt backup file.");
        }

        return restoredPark;
    }
}