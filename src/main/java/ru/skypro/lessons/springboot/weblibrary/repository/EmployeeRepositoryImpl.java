package ru.skypro.lessons.springboot.weblibrary.repository;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final List<Employee> employeeList = List.of(
            new Employee("Катя", 90000),
            new Employee("Дима", 102000),
            new Employee("Олег", 80000),
            new Employee("Вика", 165000));

    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    @Override
    public void addEmployee(List<Employee> employees) {

    }

    @Override
    public void editEmployee(Employee id, Employee employee) {

    }

}
