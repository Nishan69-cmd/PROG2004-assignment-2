import java.util.Objects;

public class Visitor {
    private int visitorID;
    private String name;
    private int age;
    private String ticketType;

    public Visitor(int visitorID, String name, int age, String ticketType) {

        if (visitorID <= 0) {
            throw new IllegalArgumentException(
                    "Visitor ID must be greater than 0");
        }

        Objects.requireNonNull(name, "Name must not be null");

        if (name.isBlank()) {
            throw new IllegalArgumentException(
                    "Name must not be blank");
        }

        if (age < 0) {
            throw new IllegalArgumentException(
                    "Age must not be negative");
        }

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

    @Override
    public String toString() {
        return "Visitor ID: " + visitorID
                + "\nName: " + name
                + "\nAge: " + age
                + "\nTicket Type: " + ticketType;
    }
}