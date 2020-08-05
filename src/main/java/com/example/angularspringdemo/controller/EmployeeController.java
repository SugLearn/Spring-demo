package com.example.angularspringdemo.controller;

//import javax.validation.Valid;
import com.example.angularspringdemo.bean.AuthenticationBean;
import com.example.angularspringdemo.model.Employee;
import com.example.angularspringdemo.repository.EmployeeRepository;
import com.example.angularspringdemo.service.EmployeeService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.angularspringdemo.bean.AuthenticationBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    @GetMapping(path = "/basicauth")
    public AuthenticationBean helloWorldBean() {
        return new AuthenticationBean("You are authenticated");
    }

    @GetMapping("/getEmployees")
    public List<Employee> getAllEmployees(){
        logger.trace("Get Employees");
        return employeeService.findAll();

    }

    @PostMapping("/addEmployees")
    public ResponseEntity<Employee> createEmployee( @RequestBody Employee employee){
        logger.trace("posted Employees");
        return ResponseEntity.ok(employeeService.createOrUpdate(employee));
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId, @RequestBody Employee employeeDetails)
            throws ResourceNotFoundException{
        Employee employee = employeeService.findById(employeeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setMobile(employeeDetails.getMobile());
        employee.setAddress(employeeDetails.getAddress());
       final Employee updateEmployee = employeeService.createOrUpdate(employee);
        logger.trace("updated the Employees");
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("deleteEmployee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException{
        Employee emp = employeeService.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employeeService.deleteById(employeeId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        logger.trace("deleted Employee");
        return response;
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeService.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found this id-"+ employeeId));
        logger.trace("GetById Employee");
        return ResponseEntity.ok().body(employee);

    }







}
