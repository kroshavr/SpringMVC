package ru.skypro.lessons.springboot.weblibrary.pojo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> showCounter() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/salary/sum")
    public int employeeSalarySum(){
        return employeeService.employeeSalarySum();
    }
    @GetMapping("/salary/min")
    public Optional<Employee> employeeSalaryMin(){
        return employeeService.employeeSalaryMin();
    }
    @GetMapping("/salary/max")
    public Optional<Employee> employeeSalaryMax(){
        return employeeService.employeeSalaryMax();
    }
    @GetMapping("/high-salary")
    public List<Employee> employeeHighSalary(){
        return employeeService.employeeHighSalary();
    }
    @PostMapping("/")
    public void addEmployee(@RequestBody List<Employee> newEmployees) {
        employeeService.addEmployee(newEmployees);
    }

    @PutMapping("/{id}")
    public void editEmployee(@PathVariable int id, @RequestBody Employee newEmployee) {
        employeeService.editEmployee(id, newEmployee);
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployeeById(@PathVariable int id) {
        return employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/salary/higher")
    public String getEmployeesWithSalaryHigherThan(@RequestParam("compareSalary") int compareSalary) {
        return employeeService.getEmployeesWithSalaryHigherThan(compareSalary);
    }
}