package co.jesus.RestaurantManager.database.operations;

import co.jesus.RestaurantManager.database.DatabaseOperation;
import co.jesus.RestaurantManager.database.DatabaseOperationHandler;
import co.jesus.RestaurantManager.entities.Employee;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeesOperations {

    public static List<Employee> getEmployees (){
        List<Employee> employees = new ArrayList<>();

        DatabaseOperation GetEmployees = new DatabaseOperation () {

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

        DatabaseOperationHandler.handreOperation(GetEmployees);

        return employees;

    }

}
