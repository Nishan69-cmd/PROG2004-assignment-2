import java.util.ArrayList;
import java.util.Collections;

public class AssignmentTwo {

    public static void main(String[] args) {

        // Part 1 - Modelling the park's people

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
    }
}