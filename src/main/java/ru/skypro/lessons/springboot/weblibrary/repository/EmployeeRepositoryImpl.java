package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.ArrayList;
import java.util.List;
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
        private final List<Employee> employeeList = List.of(
            new Employee(1,"Катя", 90000),
            new Employee(2,"Дима", 102000),
            new Employee(3,"Олег", 80000),
            new Employee(4,"Вика", 165000));

    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    @Override
    public void addEmployee(List<Employee> employees) {
        List<Employee> employeesBase = new ArrayList<>(employeeList);
        employeesBase.addAll(employees);
    }

    @Override
    public void editEmployee(Employee id, Employee employee) {
        id.setName(employee.getName());
        id.setSalary(employee.getSalary());
    }
}
