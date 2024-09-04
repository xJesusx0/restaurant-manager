package co.jesus.RestaurantManager.rest;

import co.jesus.RestaurantManager.database.DatabaseOperationHandler;
import co.jesus.RestaurantManager.database.operations.EmployeesOperations;
import co.jesus.RestaurantManager.entities.Employee;
import co.jesus.RestaurantManager.entities.ErrorResponse;
import co.jesus.RestaurantManager.entities.SimpleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("/get-employees")
    public List<Employee> getEmployees (){
        List<Employee> employeeList = EmployeesOperations.getEmployees();
        return employeeList;
    }

    @PutMapping("/update-employee-status")
    public ResponseEntity updateEmployeeStatus(@RequestBody EmployeesModificationBody employeesModificationBody){
        int id = employeesModificationBody.getEmployeeId();
        System.out.println(id);

        int rowsAffected = EmployeesOperations.changeStatus(id);

        if(rowsAffected == 0){
            return ResponseEntity.status(404)
                    .body(new ErrorResponse("No se modifico ningun usuario"));
        }

        if(rowsAffected != 1){
            return ResponseEntity.status(409)
                    .body(new ErrorResponse("Se han actualizado multiples registros"));
        }

        return ResponseEntity.status(200).body(new SimpleResponse("Actualizacion exitosa"));
    }

    @GetMapping("/get-employee-by-id")
    public ResponseEntity getEmployeeById(@RequestParam("employeeId") int employeeId){
        
        Employee employee = EmployeesOperations.getEmployeeById(employeeId);

        if(employee == null){
            return ResponseEntity.status(404)
                    .body(new ErrorResponse("No se encontro un empleado con esa id"));
        }
        return ResponseEntity.status(200)
                .body(employee);
    }

    @PutMapping("/update-employee")
    public ResponseEntity updateEmployee(@RequestBody Employee employee){

        System.out.println(employee);

        int rowsAffected = EmployeesOperations.updateEmployee(employee);

        if(rowsAffected == 0){
            return ResponseEntity.status(404)
                    .body(new ErrorResponse("No se modifico ningun usuario"));
        }

        if(rowsAffected != 1){
            return ResponseEntity.status(409)
                    .body(new ErrorResponse("Se han actualizado multiples registros"));
        }

        return ResponseEntity.status(200).body(new SimpleResponse("Actualizacion exitosa"));
    }

}

class EmployeesModificationBody {
    private int employeeId;

    public EmployeesModificationBody() {
    }

    public EmployeesModificationBody(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}

