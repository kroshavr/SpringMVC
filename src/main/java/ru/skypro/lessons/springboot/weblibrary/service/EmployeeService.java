package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.data.domain.Page;
import ru.skypro.lessons.springboot.weblibrary.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    abstract Optional<EmployeeDTO> getEmployeeById(int id);
    void addEmployee(List<EmployeeDTO> employees);

    void editEmployee(EmployeeDTO employeeDTO);

    String deleteEmployeeById(int id);

    String getEmployeesWithSalaryHigherThan(int compareSalary);

    List<EmployeeDTO> getEmployeesWithHighestSalary();

    List<EmployeeDTO> getEmployeesByPosition(String position);

    EmployeeDTO getEmployeeFullInfo(int id);

    Page <EmployeeDTO> getEmployeesByPage(int page);
}