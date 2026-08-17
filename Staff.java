import java.util.Objects;

/**
 * Represents a staff member working in the park.
 */
public class Staff {

// staff information
    private int staffID;
    private String name;
    private int age;
    private String role;

/**
* Creates a new Staff object.
*/
    public Staff(
            int staffID,
            String name,
            int age,
            String role) {

// validate staff ID
        if (staffID <= 0) {
            throw new IllegalArgumentException(
                    "Staff ID must be greater than 0");
        }

// validate staff name
        Objects.requireNonNull(
                name,
                "Name must not be null");

        if (name.isBlank()) {
            throw new IllegalArgumentException(
                    "Name must not be blank");
        }

// validate age
        if (age < 0) {
            throw new IllegalArgumentException(
                    "Age must not be negative");
        }

// validate role
        Objects.requireNonNull(
                role,
                "Role must not be null");

        if (role.isBlank()) {
            throw new IllegalArgumentException(
                    "Role must not be blank");
        }

        this.staffID = staffID;
        this.name = name;
        this.age = age;
        this.role = role;
    }

// getter methods
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

/**
* Allows a staff member to inspect an object
* that implements the Inspectable interface.
*/
    public void performInspection(
            Inspectable item,
            String result) {

        Objects.requireNonNull(
                item,
                "Inspectable item must not be null");

// close the item for inspection
        item.startInspection();

// record result and reopen the item
        item.finishInspection(result);
    }

/**
* Staff members are considered equal when
* their staff IDs are the same.
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

        Staff staff =
                (Staff) obj;

        return staffID
                == staff.staffID;
    }

/**
* Hash code is based on staff ID.
*/
    @Override
    public int hashCode() {
        return Objects.hash(staffID);
    }

/**
 * Returns staff details in a readable format.
*/
    @Override
    public String toString() {

        return "Staff ID: " + staffID
                + "\nName: " + name
                + "\nAge: " + age
                + "\nRole: " + role;
    }
}