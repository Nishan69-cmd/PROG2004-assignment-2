import java.util.ArrayList;
import java.util.Collections;

public class AssignmentTwo {

    public static void main(String[] args)
            throws InterruptedException {

// Part 1 - modelling the park's people

        System.out.println("PART 1 - PARK PEOPLE");

        Visitor visitor1 =
                new Visitor(201, "Sam", 25, "Adult");

        Visitor visitor2 =
                new Visitor(202, "Alex", 18, "Adult");

        Visitor visitor3 =
                new Visitor(203, "Mia", 30, "Adult");

        Visitor visitor4 =
                new Visitor(204, "Sam", 22, "VIP");

        Staff staff1 =
                new Staff(
                        101,
                        "John Smith",
                        30,
                        "Ride Operator");

        ArrayList<Visitor> visitors =
                new ArrayList<>();

        visitors.add(visitor1);
        visitors.add(visitor2);
        visitors.add(visitor3);

        System.out.println();
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

        System.out.println("STAFF");
        System.out.println(staff1);
        System.out.println("STAFF");
        System.out.println(staff1);

        System.out.println();
        System.out.println("TESTING EQUALITY BY ID");

        Visitor sameVisitor =
                new Visitor(
                        201,
                        "Different Name",
                        40,
                        "VIP");

        Staff sameStaff =
                new Staff(
                        101,
                        "Different Staff",
                        45,
                        "Manager");

        System.out.println(
                "Visitors with same ID are equal: "
                        + visitor1.equals(sameVisitor));

        System.out.println(
                "Staff with same ID are equal: "
                        + staff1.equals(sameStaff));
                        
// Part 2 - modelling the park's attractions


        System.out.println();
        System.out.println("PART 2 - ATTRACTIONS");

        Ride ride1 =
                new Ride(
                        301,
                        "Roller Coaster",
                        2);

        Show show1 =
                new Show(
                        401,
                        "Magic Show",
                        3);

        Toilet toilet1 =
                new Toilet(
                        501,
                        "Food Court");

        System.out.println(
                "Ride ID: "
                        + ride1.getAttractionID());

        System.out.println(
                "Ride name: "
                        + ride1.getName());

        ride1.assignOperator(staff1);
        ride1.removeOperator();

        show1.assignOperator(staff1);
        show1.removeOperator();

        staff1.performInspection(
                ride1,
                "Passed");

        System.out.println(
                "Ride result: "
                        + ride1.getInspectionResult());

        staff1.performInspection(
                toilet1,
                "Clean");

        System.out.println(
                "Toilet result: "
                        + toilet1.getInspectionResult());

// Part 3 - Waiting line

        System.out.println();
        System.out.println("PART 3 - WAITING LINE");

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

// test removing from an empty queue
        ride1.removeNextVisitor();

// Part 4 - visit history

        System.out.println();
        System.out.println("PART 4 - VISIT HISTORY");

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

// Part 5 - operating attractions
       
        System.out.println();
        System.out.println(
                "PART 5 - OPERATING ATTRACTIONS");

        Ride ride2 =
                new Ride(
                        302,
                        "Merry Go Round",
                        2);

        Show show2 =
                new Show(
                        402,
                        "Live Show",
                        3);

        System.out.println(
                "Ride capacity: "
                        + ride2.getCapacity());

        ride2.addVisitorToQueue(visitor1);

// test ride without operator
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

// serve remaining visitor
        ride2.runCycle();

// show cannot run without operator
        show2.runCycle();

        show2.assignOperator(staff1);

        System.out.println(
                "Show cycles before: "
                        + show2.getCycleCount());

// show is allowed to run with an empty queue
        show2.runCycle();

        System.out.println(
                "Show cycles after: "
                        + show2.getCycleCount());

        System.out.println();
        System.out.println(
                "TESTING RIDE WHILE CLOSED");

        ride2.addVisitorToQueue(visitor4);

        ride2.startInspection();

        ride2.runCycle();

        ride2.finishInspection("Passed");

// Part 6 - managing the park

        System.out.println();
        System.out.println(
                "PART 6 - MANAGING THE PARK");

        Park park =
                new Park();

        park.registerAttraction(ride1);
        park.registerAttraction(show1);
        park.registerAttraction(ride2);
        park.registerAttraction(show2);

        System.out.println();

        Attraction foundAttraction =
                park.findAttraction(301);

        System.out.println(
                "Found attraction: "
                        + foundAttraction.getName());

        System.out.println();

// test an attraction that does not exist
        park.findAttraction(999);

        System.out.println();

        park.displayAttractionVisitorCounts();

        show1.recordVisit(visitor1);
        show1.recordVisit(visitor3);

        System.out.println();

        park.displayAttractionVisitorCounts();

        System.out.println();

        park.getDistinctVisitorCount();

        System.out.println(
                "Waiting visitors for Merry Go Round: "
                        + ride2
                                .getWaitingVisitors()
                                .size());

// Part 7 - backup and restore

        System.out.println();
        System.out.println(
                "PART 7 - BACKUP AND RESTORE");

        ParkIO.savePark(
                park,
                "park_backup.txt");

        System.out.println();
        System.out.println(
                "READING BACKUP FILE");

        ParkIO.readBackupFile(
                "park_backup.txt");

        System.out.println();
        System.out.println(
                "RESTORING PARK");

        Park restoredPark =
                ParkIO.restorePark(
                        "park_backup.txt");

        System.out.println(
                "Restored attractions: "
                        + restoredPark
                                .getAttractions()
                                .size());

        Attraction restoredRide =
                restoredPark.findAttraction(302);

        System.out.println(
                "Restored waiting visitors: "
                        + restoredRide
                                .getWaitingVisitors()
                                .size());

        System.out.println(
                "Restored Merry Go Round visits: "
                        + restoredRide
                                .getVisitCount());


// test missing backup file

        System.out.println();
        System.out.println(
                "TESTING MISSING BACKUP FILE");

        Park missingFilePark =
                ParkIO.restorePark(
                        "missing_file.txt");

        System.out.println(
                "Attractions restored from missing file: "
                        + missingFilePark
                                .getAttractions()
                                .size());


// test corrupt backup file

        System.out.println();
        System.out.println(
                "TESTING CORRUPT BACKUP FILE");

        Park corruptPark =
                ParkIO.restorePark(
                        "corrupt_backup.txt");

        System.out.println(
                "Attractions restored from corrupt file: "
                        + corruptPark
                                .getAttractions()
                                .size());


// compare original and restored park

        System.out.println();
        System.out.println(
                "CHECKING RESTORED PARK");

        System.out.println(
                "Original attractions: "
                        + park
                                .getAttractions()
                                .size());

        System.out.println(
                "Restored attractions: "
                        + restoredPark
                                .getAttractions()
                                .size());

        Attraction originalRide =
                park.findAttraction(302);

        System.out.println(
                "Original waiting visitors: "
                        + originalRide
                                .getWaitingVisitors()
                                .size());

        System.out.println(
                "Restored waiting visitors: "
                        + restoredRide
                                .getWaitingVisitors()
                                .size());

        System.out.println(
                "Original visit count: "
                        + originalRide
                                .getVisitCount());

        System.out.println(
                "Restored visit count: "
                        + restoredRide
                                .getVisitCount());

        System.out.println(
                "Original distinct visitors: "
                        + park
                                .getDistinctVisitorCount());

        System.out.println(
                "Restored distinct visitors: "
                        + restoredPark
                                .getDistinctVisitorCount());

// Part 8 - concurrency

        System.out.println();
        System.out.println(
                "PART 8 - CONCURRENCY");

        Park concurrentPark =
                new Park();

        Ride concurrentRide =
                new Ride(
                        601,
                        "Sky Ride",
                        2);

        Show concurrentShow =
                new Show(
                        602,
                        "Dance Show",
                        2);

        Staff staff2 =
                new Staff(
                        102,
                        "Jane Brown",
                        28,
                        "Ride Operator");

        Staff staff3 =
                new Staff(
                        103,
                        "David Lee",
                        32,
                        "Show Operator");

        concurrentRide.assignOperator(staff2);
        concurrentShow.assignOperator(staff3);

        concurrentRide.addVisitorToQueue(visitor1);
        concurrentRide.addVisitorToQueue(visitor2);

        concurrentShow.addVisitorToQueue(visitor3);
        concurrentShow.addVisitorToQueue(visitor4);

        concurrentPark.registerAttraction(
                concurrentRide);

        concurrentPark.registerAttraction(
                concurrentShow);

        AttractionTask task1 =
                new AttractionTask(
                        concurrentRide,
                        concurrentPark);

        AttractionTask task2 =
                new AttractionTask(
                        concurrentShow,
                        concurrentPark);

        Thread thread1 =
                new Thread(task1);

        Thread thread2 =
                new Thread(task2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(
                "Total visitors served concurrently: "
                        + concurrentPark
                                .getTotalVisitorsServed());
    }
}