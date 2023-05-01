package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    public int employeeSalarySum();

    public Optional<Employee> employeeSalaryMin();

    public Optional<Employee> employeeSalaryMax();

    public List<Employee> employeeHighSalary();

}