package co.jesus.RestaurantManager.database.operations;

import co.jesus.RestaurantManager.database.DatabaseOperation;
import co.jesus.RestaurantManager.database.DatabaseOperationHandler;
import co.jesus.RestaurantManager.entities.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeesOperations {

    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        DatabaseOperation GetEmployees = new DatabaseOperation() {

            @Override
            public void execute(Connection connection) throws SQLException {
                String query = "SELECT * FROM employees";

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int employeeId = resultSet.getInt("employee_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    java.sql.Date hireDateSql = resultSet.getDate("hire_date");
                    LocalDate hireDate = hireDateSql.toLocalDate();
                    String position = resultSet.getString("position");
                    String status = resultSet.getString("status");

                    Employee employee = new Employee(employeeId, firstName, lastName, email, phone, hireDate, position, status);

                    employees.add(employee);
                }

            }
        };

        DatabaseOperationHandler.handleOperation(GetEmployees);

        return employees;

    }

    public static Employee getEmployeeById(int employeeId){

        Employee[] employees = new Employee[1];

        DatabaseOperation GetEmployeeById = new DatabaseOperation() {
            @Override
            public void execute(Connection connection) throws SQLException {

                try{
                    String query = "SELECT * FROM employees WHERE employee_id = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1,employeeId);

                    ResultSet resultSet = statement.executeQuery();

                    if(resultSet.next()){
                        int empId = resultSet.getInt("employee_id");
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                        String email = resultSet.getString("email");
                        String phone = resultSet.getString("phone");
                        java.sql.Date hireDateSql = resultSet.getDate("hire_date");
                        LocalDate hireDate = hireDateSql.toLocalDate();
                        String position = resultSet.getString("position");
                        String status = resultSet.getString("status");

                        Employee employee = new Employee(empId, firstName, lastName, email, phone, hireDate, position, status);
                        employees[0] = employee;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        DatabaseOperationHandler.handleOperation(GetEmployeeById);

        return employees[0];

    }

    public static int changeStatus(int employeeId) {
        int[] rowsAffectedStatus = {0};
        DatabaseOperation ChangeStatus = new DatabaseOperation() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try {
                    String query = """
                                UPDATE employees SET status = CASE 
                                    WHEN status = 'activo' THEN 'inactivo'
                                    WHEN status = 'inactivo' THEN 'activo'
                                    ELSE status
                                END
                                WHERE employee_id = ?
                            """;
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, employeeId);

                    int rowsAffected = statement.executeUpdate();
                    rowsAffectedStatus[0] = rowsAffected;
                    if (rowsAffected > 0) {
                        System.out.println("Estado del empleado actualizado correctamente.");
                    } else {
                        System.out.println("No se encontró ningún empleado con el ID especificado.");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            };
        };

        DatabaseOperationHandler.handleOperation(ChangeStatus);

        return rowsAffectedStatus[0];
    }

    public static int updateEmployee (Employee employee){
        int[] rowsAffectedContainer = {0};

        DatabaseOperation UpdateEmployee = new DatabaseOperation() {
            @Override
            public void execute(Connection connection) throws SQLException {
                String query = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, phone = ?, hire_date = ?, position = ? WHERE employee_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getLastName());
                statement.setString(3, employee.getEmail());
                statement.setString(4, employee.getPhone());

                LocalDate hireDate = employee.getHireDate();
                java.sql.Date sqlHireDate = java.sql.Date.valueOf(hireDate);

                statement.setDate(5, sqlHireDate);
                statement.setString(6, employee.getPosition());
                statement.setInt(7, employee.getEmployeeId()); // Suponiendo que employee_id es un entero

                int rowsAffected = statement.executeUpdate();

                rowsAffectedContainer[0] = rowsAffected;

            }
        };

        DatabaseOperationHandler.handleOperation(UpdateEmployee);

        return rowsAffectedContainer[0];
    }
}