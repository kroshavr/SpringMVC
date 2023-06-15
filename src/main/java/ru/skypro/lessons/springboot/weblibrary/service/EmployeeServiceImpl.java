package ru.skypro.lessons.springboot.weblibrary.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Position;
import ru.skypro.lessons.springboot.weblibrary.entity.Report;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary.repository.ReportRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ReportRepository reportRepository;
    private final ObjectMapper objectMapper;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ReportRepository reportRepository, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.reportRepository = reportRepository;
        this.objectMapper = objectMapper;
    }
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.getAllEmployees();
        return employees.stream()
                .map(EmployeeDTO ::fromEmployee)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<EmployeeDTO> getEmployeeById(int id) throws Exception {
        return Optional.ofNullable(employeeRepository.findById(id).stream()
                .map(EmployeeDTO::fromEmployee)
                .findAny().orElseThrow(Exception::new));
    }
    @Override
    public void addEmployee(List<EmployeeDTO> employeeDTO) {
            List<Employee> employees = employeeDTO.stream()
                    .map(EmployeeDTO::toEmployee)
                    .toList();
            employeeRepository.saveAll(employees);
    }

    @Override
    public void editEmployee(int id, Employee updatedEmployee) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(Exception::new);
        employee.setName(updatedEmployee.getName());
        employee.setSalary(updatedEmployee.getSalary());
        employee.setPosition(new Position(updatedEmployee.getPosition().getId(), updatedEmployee.getPosition().getName()));
        employeeRepository.save(employee);
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
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return EmployeeDTO.fromEmployee(employee);
    }

    @Override
    public Page<EmployeeDTO> getEmployeesByPage(int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(EmployeeDTO::fromEmployee);
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        int streamSize = inputStream.available();
        byte[] bytes = new byte[streamSize];
        inputStream.read(bytes);
        String json = new String(bytes, StandardCharsets.UTF_8);
        List<Employee> employees = objectMapper.readValue(json, new TypeReference<>(){});
        employeeRepository.saveAll(employees);
    }

    @Override
    public int createReport() throws IOException {
        List<ReportDTO> reportEntries = reportRepository.getReport();
        String json = objectMapper.writeValueAsString(reportEntries);
        Report report = new Report(json);
        reportRepository.save(report);
        return report.getId();
    }

    @Override
    public ResponseEntity<ByteArrayResource> downloadFileById(int id) throws FileNotFoundException {
        Report report = reportRepository.findById(id).orElseThrow(FileNotFoundException::new);
        String json = report.getJson();
        ByteArrayResource resource =  new ByteArrayResource(json.getBytes());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report.json\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }
}