import java.util.ArrayList;
import java.util.Collections;

public class AssignmentTwo {

    public static void main(String[] args) {


        Visitor visitor1 =
                new Visitor(201, "Sam", 25, "Adult");

        Visitor visitor2 =
                new Visitor(202, "Alex", 18, "Adult");

        Visitor visitor3 =
                new Visitor(203, "Mia", 30, "Adult");

        Visitor visitor4 =
                new Visitor(204, "Sam", 22, "VIP");

        ArrayList<Visitor> visitors = new ArrayList<>();

        visitors.add(visitor1);
        visitors.add(visitor2);
        visitors.add(visitor3);

        System.out.println("VISITORS BEFORE SORTING");

        for (Visitor visitor : visitors) {
            System.out.println(visitor);
            System.out.println();
        }

        Collections.sort(visitors);

        System.out.println("VISITORS SORTED BY AGE");

        for (Visitor visitor : visitors) {
            System.out.println(visitor);
            System.out.println();
        }


        System.out.println();
        System.out.println("ATTRACTIONS");

        Staff staff1 = new Staff(
                101,
                "John Smith",
                30,
                "Ride Operator");

        Ride ride1 = new Ride(
                301,
                "Roller Coaster",
                2);
        Show show1 = new Show(
                401,
                "Magic Show",
                3);
        Toilet toilet1 =
                new Toilet(501, "Food Court");

        System.out.println(ride1.getAttractionID());
        System.out.println(ride1.getName());

        ride1.assignOperator(staff1);
        ride1.removeOperator();

        show1.assignOperator(staff1);
        show1.removeOperator();

        staff1.performInspection(ride1, "Passed");

        System.out.println(
                "Ride result: "
                        + ride1.getInspectionResult());

        staff1.performInspection(toilet1, "Clean");

        System.out.println(
                "Toilet result: "
                        + toilet1.getInspectionResult());


        System.out.println();
        System.out.println(" WAITING LINE");

        ride1.addVisitorToQueue(visitor1);
        ride1.addVisitorToQueue(visitor2);
        ride1.addVisitorToQueue(visitor3);

        System.out.println();
        ride1.displayWaitingLine();

        Visitor nextVisitor =
                ride1.removeNextVisitor();

        System.out.println(
                "Next visitor: "
                        + nextVisitor.getName());

        System.out.println();
        ride1.displayWaitingLine();

        System.out.println();
        System.out.println(
                "REMOVING REMAINING VISITORS");

        ride1.removeNextVisitor();
        ride1.removeNextVisitor();

        System.out.println();
        ride1.displayWaitingLine();

        System.out.println();
        ride1.removeNextVisitor();

        System.out.println();
        System.out.println("VISIT HISTORY");

        ride1.recordVisit(visitor1);
        ride1.recordVisit(visitor2);
        ride1.recordVisit(visitor1);
        ride1.recordVisit(visitor4);

        System.out.println();
        ride1.displayVisitHistory();

        System.out.println(
                "Total visits: "
                        + ride1.getVisitCount());

        System.out.println(
                "Has Sam visited: "
                        + ride1.hasVisited(visitor1));

        System.out.println(
                "Has Mia visited: "
                        + ride1.hasVisited(visitor3));

        System.out.println();
        ride1.displayHistoryByAge();

        System.out.println();
        ride1.displayHistoryByNameAndTicket();
        System.out.println("Ride capacity: "
        + ride1.getCapacity());

        System.out.println("Ride cycles: "
                + ride1.getCycleCount());

        System.out.println();
        System.out.println("OPERATING ATTRACTIONS");

        Ride ride2 = new Ride(
                302,
                "Merry Go Round",
                2);

        Show show2 = new Show(
                402,
                "Live Show",
                3);
                ride2.addVisitorToQueue(visitor1);

        ride2.runCycle();
        ride2.assignOperator(staff1);

        ride2.addVisitorToQueue(visitor2);
        ride2.addVisitorToQueue(visitor3);

        System.out.println(
                "Cycles before: "
                        + ride2.getCycleCount());

        ride2.runCycle();

        System.out.println(
                "Cycles after: "
                        + ride2.getCycleCount());
                        System.out.println();
        ride2.displayWaitingLine();

        System.out.println();
        ride2.displayVisitHistory();
        ride2.runCycle();
        show2.runCycle();
        show2.assignOperator(staff1);
        System.out.println(
        "Show cycles before: "
                + show2.getCycleCount());

        show2.runCycle();

        System.out.println(
                "Show cycles after: "
                        + show2.getCycleCount());
                        ride2.startInspection();

        ride2.runCycle();

        ride2.finishInspection("Passed");
        System.out.println();
        System.out.println("TESTING RIDE WHILE CLOSED");

        ride2.addVisitorToQueue(visitor4);

        ride2.startInspection();

        ride2.runCycle();

        ride2.finishInspection("Passed");

        System.out.println();
        System.out.println(" MANAGING THE PARK");

        Park park = new Park();

        park.registerAttraction(ride1);
        park.registerAttraction(show1);
        park.registerAttraction(ride2);
        park.registerAttraction(show2);
        Attraction foundAttraction =
        park.findAttraction(301);

        System.out.println(
                "Found attraction: "
                        + foundAttraction.getName());
                        System.out.println();
               
        park.findAttraction(999);

        park.displayAttractionVisitorCounts();
        show1.recordVisit(visitor1);
        show1.recordVisit(visitor3);

        System.out.println();

        park.displayAttractionVisitorCounts();

        System.out.println();

        park.getDistinctVisitorCount();
        System.out.println();
        System.out.println(
        "Waiting visitors for Merry Go Round: "
                + ride2.getWaitingVisitors().size());
        System.out.println(" BACKUP AND RESTORE");

        ParkIO.savePark(
                park,
                "park_backup.txt");
                   System.out.println();
        System.out.println("READING BACKUP FILE");

        ParkIO.readBackupFile(
                "park_backup.txt");
                System.out.println();
System.out.println("RESTORING PARK");

Park restoredPark =
        ParkIO.restorePark(
                "park_backup.txt");

System.out.println(
        "Restored attractions: "
                + restoredPark.getAttractions().size());

restoredPark.findAttraction(302);
Attraction restoredRide =
        restoredPark.findAttraction(302);

System.out.println(
        "Restored waiting visitors: "
                + restoredRide
                        .getWaitingVisitors()
                        .size());
                        System.out.println(
        "Restored Merry Go Round visits: "
                + restoredRide.getVisitCount());

                                System.out.println();
System.out.println("TESTING MISSING BACKUP FILE");

Park missingFilePark =
        ParkIO.restorePark(
                "missing_file.txt");

System.out.println(
        "Attractions restored from missing file: "
                + missingFilePark.getAttractions().size());
                
System.out.println();
System.out.println("TESTING CORRUPT BACKUP FILE");

Park corruptPark =
        ParkIO.restorePark(
                "corrupt_backup.txt");

System.out.println(
        "Attractions restored from corrupt file: "
                + corruptPark.getAttractions().size());
                System.out.println();
System.out.println("CHECKING RESTORED PARK");

System.out.println(
        "Original attractions: "
                + park.getAttractions().size());

System.out.println(
        "Restored attractions: "
                + restoredPark.getAttractions().size());

Attraction originalRide =
        park.findAttraction(302);

System.out.println(
        "Original waiting visitors: "
                + originalRide.getWaitingVisitors().size());

System.out.println(
        "Restored waiting visitors: "
                + restoredRide.getWaitingVisitors().size());

System.out.println(
        "Original visit count: "
                + originalRide.getVisitCount());

System.out.println(
        "Restored visit count: "
                + restoredRide.getVisitCount());

System.out.println(
        "Original distinct visitors: "
                + park.getDistinctVisitorCount());

System.out.println(
        "Restored distinct visitors: "
                + restoredPark.getDistinctVisitorCount());
        }
}