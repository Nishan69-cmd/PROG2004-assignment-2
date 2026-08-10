import java.util.Objects;

public abstract class Attraction {

    private int attractionID;
    private String name;
    private Staff operator;

  public Attraction(int attractionID, String name) {

    if (attractionID <= 0) {
        throw new IllegalArgumentException(
                "Attraction ID must be greater than 0");
    }

    Objects.requireNonNull(name, "Name must not be null");

    if (name.isBlank()) {
        throw new IllegalArgumentException(
                "Name must not be blank");
    }

    this.attractionID = attractionID;
    this.name = name;
    this.operator = null;
    }
    public int getAttractionID() {
    return attractionID;
    }

    public String getName() {
        return name;
    }

    public Staff getOperator() {
        return operator;
    }
    public void assignOperator(Staff operator) {

    Objects.requireNonNull(operator, "Operator must not be null");

    this.operator = operator;

    System.out.println(operator.getName()
            + " assigned to " + name);
    }

    public void removeOperator() {

        if (operator == null) {
            System.out.println("No operator assigned to " + name);
        } else {
            System.out.println(operator.getName()
                    + " removed from " + name);

            operator = null;
        }
    }
}

