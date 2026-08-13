import java.util.Objects;

public class Staff {

    private int staffID;
    private String name;
    private int age;
    private String role;

    public Staff(int staffID, String name, int age, String role) {

        if (staffID <= 0) {
            throw new IllegalArgumentException(
                    "Staff ID must be greater than 0");
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

        Objects.requireNonNull(role, "Role must not be null");

        if (role.isBlank()) {
            throw new IllegalArgumentException(
                    "Role must not be blank");
        }

        this.staffID = staffID;
        this.name = name;
        this.age = age;
        this.role = role;
    }

    public int getStaffID() {
        return staffID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }

   
    public void performInspection(Inspectable item, String result) {

    Objects.requireNonNull(
            item,
            "Inspectable item must not be null");

    item.startInspection();

    item.finishInspection(result);
}

    @Override
    public String toString() {
        return "Staff ID: " + staffID
                + "\nName: " + name
                + "\nAge: " + age
                + "\nRole: " + role;
    }
}