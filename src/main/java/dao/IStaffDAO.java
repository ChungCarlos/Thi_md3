package dao;

import model.Staff;

import java.sql.SQLException;
import java.util.List;

public interface IStaffDAO {
    public void insertStaff(Staff staff) throws SQLException;
    public Staff selectStaff(int id);
    public List<Staff> selectAllUsers();

    public boolean deleteStaff(int id) throws SQLException;

    public boolean updateStaff(Staff staff) throws SQLException;

    Staff getStaffById(int id);
}
