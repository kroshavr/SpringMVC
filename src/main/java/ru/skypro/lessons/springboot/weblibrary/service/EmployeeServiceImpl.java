package ru.skypro.lessons.springboot.weblibrary.service;


import org.springframework.beans.factory.annotation.Autowired;
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
        return Optional.ofNullable(min.orElse(null));
    }

    @Override
    public Optional<Employee> employeeSalaryMax() {
        Optional<Employee> max = employeeRepository.getAllEmployees().stream()
                .max(Comparator.comparing(Employee::getSalary));
        return Optional.ofNullable(max.orElse(null));
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

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.getAllEmployees().stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }
    @Override
    public void addEmployee(List<Employee> employees) {
        employeeRepository.addEmployee(employees);
    }

    @Override
    public void editEmployee(int id, Employee newEmployee) {
        employeeRepository.editEmployee(getEmployeeById(id).orElse(null), newEmployee);
    }

    @Override
    public String deleteEmployeeById(int id) {
        employeeRepository.getAllEmployees().removeIf(employee -> employee.getId() == id);
        return "Сотрудник c id = " + id + " успешно удален из базы данных";
    }

    @Override
    public String getEmployeesWithSalaryHigherThan(int compareSalary) {
        List<Employee> employeeHighSalary = employeeRepository.getAllEmployees().stream()
                .filter(employee -> employee.getSalary() > compareSalary)
                .toList();
        return "Сотрудники с зарплатой выше " + compareSalary + ": " + employeeHighSalary;
    }

}