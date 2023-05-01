package ru.skypro.lessons.springboot.weblibrary.pojo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

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
}