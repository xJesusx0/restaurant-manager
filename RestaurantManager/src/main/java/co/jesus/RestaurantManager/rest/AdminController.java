package co.jesus.RestaurantManager.rest;

import co.jesus.RestaurantManager.database.operations.EmployeesOperations;
import co.jesus.RestaurantManager.entities.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("/get-employees")
    public List<Employee> getEmployees (){

        List<Employee> employeeList = EmployeesOperations.getEmployees();
        return employeeList;
    }

}
