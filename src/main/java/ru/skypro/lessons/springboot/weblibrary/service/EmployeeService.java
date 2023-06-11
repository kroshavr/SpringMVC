package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import ru.skypro.lessons.springboot.weblibrary.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    Optional<Employee> getEmployeeById(int id);
    void addEmployee(List<EmployeeDTO> employees);

    void editEmployee(EmployeeDTO employeeDTO);

    String deleteEmployeeById(int id);

    String getEmployeesWithSalaryHigherThan(int compareSalary);

    List<EmployeeDTO> getEmployeesWithHighestSalary();

    List<EmployeeDTO> getEmployeesByPosition(String position);

    EmployeeDTO getEmployeeFullInfo(int id);

    Page <EmployeeDTO> getEmployeesByPage(int page);

    int createReport() throws IOException;

    ResponseEntity<Resource> downloadFileById(int id) throws FileNotFoundException;
}