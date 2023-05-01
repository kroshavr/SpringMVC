package ru.skypro.lessons.springboot.weblibrary.service;


import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.*;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public int employeeSalarySum() {
        int sum = employeeRepository.getAllEmployees().stream()
                .mapToInt(Employee::getSalary)
                .sum();
        return sum;
    }

    @Override
    public Optional<Employee> employeeSalaryMin() {
        Optional<Employee> min = employeeRepository.getAllEmployees().stream()
                .min(Comparator.comparing(Employee::getSalary));
        return min;
    }

    @Override
    public Optional<Employee> employeeSalaryMax() {
        Optional<Employee> max = employeeRepository.getAllEmployees().stream()
                .max(Comparator.comparing(Employee::getSalary));
        return max;
    }

    @Override
    public List<Employee> employeeHighSalary() {
        double average = employeeRepository.getAllEmployees().stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElse(0);
        List<Employee> high = new ArrayList<>();
        for (Employee employee : employeeRepository.getAllEmployees()) {
            if (employee.getSalary() > average) {
                high.add(employee);
            }
        }
        return high;
    }
}