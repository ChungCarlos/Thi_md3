package model;

public class Staff {
    private int id;
    private String Name;
    private String Email;
    private String Address;
    private String PhoneNumber;
    private float Salary;
    private String Department;

    public Staff() {
    }

    public Staff(int id, String name, String email, String address, String phoneNumber, float salary, String department) {
        this.id = id;
        Name = name;
        Email = email;
        Address = address;
        PhoneNumber = phoneNumber;
        Salary = salary;
        Department = department;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public float getSalary() {
        return Salary;
    }

    public void setSalary(float salary) {
        Salary = salary;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }
}
