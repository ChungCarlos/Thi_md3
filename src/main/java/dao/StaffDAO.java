package dao;

import model.Department;
import model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO implements IStaffDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo";
    private String jdbcUsername = "root";
    private String jdbcPassword = "C0223g1@";

    private static final String INSERT_STAFF_SQL = "INSERT INTO staff " + "  (name, email, address,phoneNumber,salary,department) VALUES " +
            " (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ALL_STAFF = "select * from staff";
    private static final String SELECT_STAFF_BY_ID = "select id,name,email,Address,PhoneNumber,salary,department from staff where id =?";
    private static final String DELETE_STAFF_SQL = "delete from staff where id = ?;";
    private static final String UPDATE_STAFF_SQL = "update staff set name = ?,email= ?, Address =?, PhoneNumber =?, Salary = ?, Department = ? where id = ?;";

    public StaffDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertStaff(Staff staff) throws SQLException {
        System.out.println(INSERT_STAFF_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STAFF_SQL)) {
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getEmail());
            preparedStatement.setString(3, staff.getAddress());
            preparedStatement.setString(4, staff.getPhoneNumber());
            preparedStatement.setFloat(5, staff.getSalary());
            preparedStatement.setString(6, staff.getDepartment());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public Staff selectStaff(int id) {
        Staff staff = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                Float salary = rs.getFloat("salary");
                String department = rs.getString("department");
                staff = new Staff(id, name, email, address,phoneNumber,salary,department);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return staff;
    }

    public List<Staff> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Staff> staff = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STAFF);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                Float salary = rs.getFloat("salary");
                String department = rs.getString("department");
                staff.add(new Staff(id, name, email, address,phoneNumber,salary,department));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return staff;
    }

    public boolean deleteStaff(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_STAFF_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateStaff(Staff staff) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STAFF_SQL);) {
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getEmail());
            preparedStatement.setString(3, staff.getAddress());
            preparedStatement.setString(4, staff.getPhoneNumber());
            preparedStatement.setFloat(5, staff.getSalary());
            preparedStatement.setString(6, staff.getDepartment());
            preparedStatement.setInt(6, staff.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    @Override
    public Staff getStaffById(int id) {
        Staff staff = null;
        String query = "{CALL get_user_by_id(?)}";

        // Thiết lập kết nối
        try (
                Connection connection = getConnection();
                CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                Float salary = resultSet.getFloat("salary");
                String department = resultSet.getString("department");
                staff = new Staff(id, name, email, address,phoneNumber,salary,department);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return staff;
    }
}
