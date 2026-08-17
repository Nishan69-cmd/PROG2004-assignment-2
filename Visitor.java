import java.util.Objects;

/**
 * Represents a visitor in the amusement park.
 * Visitors are naturally sorted by age.
 */
public class Visitor implements Comparable<Visitor> {

    // Visitor information
    private int visitorID;
    private String name;
    private int age;
    private String ticketType;
/**
      Creates a new Visitor object.
*/
    public Visitor(
            int visitorID,
            String name,
            int age,
            String ticketType) {

// validate visitor ID
        if (visitorID <= 0) {
            throw new IllegalArgumentException(
                    "Visitor ID must be greater than 0");
        }

        // Validate visitor name
        Objects.requireNonNull(
                name,
                "Name must not be null");

        if (name.isBlank()) {
            throw new IllegalArgumentException(
                    "Name must not be blank");
        }

// validate visitor age
        if (age < 0) {
            throw new IllegalArgumentException(
                    "Age must not be negative");
        }

// validate ticket type
        Objects.requireNonNull(
                ticketType,
                "Ticket type must not be null");

        if (ticketType.isBlank()) {
            throw new IllegalArgumentException(
                    "Ticket type must not be blank");
        }

        this.visitorID = visitorID;
        this.name = name;
        this.age = age;
        this.ticketType = ticketType;
    }

// getter methods
    public int getVisitorID() {
        return visitorID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getTicketType() {
        return ticketType;
    }

/**
* Natural ordering of visitors is based on age.
*/
    @Override
    public int compareTo(Visitor other) {

        if (this.age < other.age) {
            return -1;

        } else if (this.age > other.age) {
            return 1;

        } else {
            return 0;
        }
    }

/**
 * Visitors are considered equal when they have
    * the same visitor ID.
*/
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null
                || getClass() != obj.getClass()) {
            return false;
        }

        Visitor visitor =
                (Visitor) obj;

        return visitorID
                == visitor.visitorID;
    }

/**
* Hash code is also based on visitor ID so it
* remains consistent with equals().
*/
    @Override
    public int hashCode() {
        return Objects.hash(visitorID);
    }
/**
 * Returns visitor details in a readable form.
*/
    @Override
    public String toString() {

        return "Visitor ID: " + visitorID
                + "\nName: " + name
                + "\nAge: " + age
                + "\nTicket Type: " + ticketType;
    }
}