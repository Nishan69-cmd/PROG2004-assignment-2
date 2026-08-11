import java.util.ArrayList;
import java.util.Collections;

public class AssignmentTwo {

    public static void main(String[] args) {

        Visitor visitor1 = new Visitor(201, "Sam", 25, "Adult");
        Visitor visitor2 = new Visitor(202, "Alex", 18, "Adult");
        Visitor visitor3 = new Visitor(203, "Mia", 30, "Adult");

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

     Staff staff1 = new Staff(
            101,
            "John Smith",
            30,
            "Ride Operator");

        Ride ride1 = new Ride(301, "Roller Coaster");

        System.out.println(ride1.getAttractionID());
        System.out.println(ride1.getName());

        ride1.assignOperator(staff1);

        System.out.println(ride1.getOperator());

        ride1.removeOperator();
        Show show1 = new Show(401, "Magic Show");

    System.out.println(show1.getAttractionID());
    System.out.println(show1.getName());

    show1.assignOperator(staff1);
    show1.removeOperator();
    ride1.inspect("Passed");

    System.out.println(
            "Inspection result: "
                    + ride1.getInspectionResult());

    System.out.println(
            "Ride closed: "
                    + ride1.isClosed());
                    Toilet toilet1 = new Toilet(501, "Food Court");

    toilet1.inspect("Clean");

    System.out.println(
            "Inspection result: "
                    + toilet1.getInspectionResult());

    System.out.println(
            "Toilet closed: "
                    + toilet1.isClosed());
                    staff1.performInspection(ride1, "Passed");

    System.out.println("Ride result: "
            + ride1.getInspectionResult());
            staff1.performInspection(toilet1, "Clean");

    System.out.println("Toilet result: "
            + toilet1.getInspectionResult());
    }
}