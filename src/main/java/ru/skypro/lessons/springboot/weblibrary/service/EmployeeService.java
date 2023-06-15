package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    Optional<EmployeeDTO> getEmployeeById(int id) throws Exception;
    void addEmployee(List<EmployeeDTO> employees);

    void editEmployee(int id, Employee updatedEmployee) throws Exception;

    String deleteEmployeeById(int id);

    String getEmployeesWithSalaryHigherThan(int compareSalary);

    List<EmployeeDTO> getEmployeesWithHighestSalary();

    List<EmployeeDTO> getEmployeesByPosition(String position);

    EmployeeDTO getEmployeeFullInfo(int id);

    Page <EmployeeDTO> getEmployeesByPage(int page);

    void uploadFile(MultipartFile file) throws IOException;

    int createReport() throws IOException;

    ResponseEntity<ByteArrayResource> downloadFileById(int id) throws FileNotFoundException;

}