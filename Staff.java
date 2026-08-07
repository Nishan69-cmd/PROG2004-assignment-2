public class Staff {
    private int staffID;
    private String name;
    private int age;
    private String role;

    public Staff(int staffID, String name, int age, String role){
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
    @Override
    public String toString() {
        return "Staff ID: " + staffID
                + "\nName: " + name
                + "\nAge: " + age
                + "\nRole: " + role;
    }
}

