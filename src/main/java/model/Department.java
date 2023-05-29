package model;

public class Department {
    private int Department_id;
    private String Name;

    public Department(int department_id, String name) {
        Department_id = department_id;
        Name = name;
    }

    public Department() {
    }

    public int getDepartment_id() {
        return Department_id;
    }

    public void setDepartment_id(int department_id) {
        Department_id = department_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
