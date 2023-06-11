package ru.skypro.lessons.springboot.weblibrary.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Report;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary.repository.ReportRepository;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ReportRepository reportRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ReportRepository reportRepository) {
        this.employeeRepository = employeeRepository;
        this.reportRepository = reportRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.getAllEmployees();
        return employees.stream()
                .map(EmployeeDTO ::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }
    @Override
    public void addEmployee(List<EmployeeDTO> employees) {
        employeeRepository.save(new Employee());
    }

    @Override
    public void editEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(new Employee());
    }

    @Override
    public String deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
        return "Сотрудник c id = " + id + " успешно удален из базы данных";
    }

    @Override
    public String getEmployeesWithSalaryHigherThan(int compareSalary) {
        List<EmployeeDTO> employeeHighSalary = employeeRepository.getAllEmployees().stream()
                .filter(employee -> employee.getSalary() > compareSalary)
                .map(EmployeeDTO::fromEmployee)
                .toList();
        return "Сотрудники с зарплатой выше " + compareSalary + ": " + employeeHighSalary;
    }

    public List<EmployeeDTO> getEmployeesWithHighestSalary() {
        List<Employee> employees = employeeRepository.getEmployeesWithHighestSalary();
        return employees.stream()
                .map(EmployeeDTO::fromEmployee)
                .toList();
    }

    @Override
    public List<EmployeeDTO> getEmployeesByPosition(String position) {
        List<Employee> employees;
        if (position != null) {
            employees = employeeRepository.findByPositionName(position);
        } else {
            employees = (List<Employee>) employeeRepository.findAll();
        }
        return employees.stream()
                .map(EmployeeDTO::fromEmployee)
                .toList();
    }

    @Override
    public EmployeeDTO getEmployeeFullInfo(int id) {
        Employee employee = (Employee) employeeRepository.findById(id).orElseThrow();
        return EmployeeDTO.fromEmployee(employee);
    }

    @Override
    public Page<EmployeeDTO> getEmployeesByPage(int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(EmployeeDTO::fromEmployee);
    }

    @Override
    public int createReport() throws IOException {
        List<ReportDTO> reportEntries = reportRepository.getReport();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(reportEntries);
        Report report = new Report(json);
        reportRepository.save(report);
        return report.getId();
    }

    @Override
    public ResponseEntity<Resource> downloadFileById(int id) throws FileNotFoundException {
        Report report = reportRepository.findById(id).orElseThrow(FileNotFoundException::new);
        String json = report.getJson();
        Resource resource = (Resource) new ByteArrayResource(json.getBytes());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report.json\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }
}