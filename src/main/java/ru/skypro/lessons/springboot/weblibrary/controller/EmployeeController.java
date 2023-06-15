package ru.skypro.lessons.springboot.weblibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> showCounter() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/")
    public void addEmployee(@RequestBody List<EmployeeDTO> employees) {
        employeeService.addEmployee(employees);
    }

    @PutMapping("/{id}")
    public void editEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) throws Exception {
        employeeService.editEmployee(id, updatedEmployee);
    }

    @GetMapping("/{id}")
    public Optional<EmployeeDTO> getEmployeeById(@PathVariable int id) throws Exception {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployeeById(@PathVariable int id) {
        return employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/salary/higher")
    public String getEmployeesWithSalaryHigherThan(@RequestParam("compareSalary") int compareSalary) {
        return employeeService.getEmployeesWithSalaryHigherThan(compareSalary);
    }

    @PostMapping("/salary/highestSalary")
    public List<EmployeeDTO> getEmployeesWithHighestSalary() {
        return employeeService.getEmployeesWithHighestSalary();
    }

    @GetMapping("/position")
    public List<EmployeeDTO> getEmployeesByPosition(@RequestParam(name = "position", required = false) String position) {
        return employeeService.getEmployeesByPosition(position);
    }

    @GetMapping("/{id}/fullInfo")
    public EmployeeDTO getEmployeeFullInfo(@PathVariable int id) {
        return employeeService.getEmployeeFullInfo(id);
    }

    @GetMapping("/page")
    public Page <EmployeeDTO> getEmployeesByPage(@RequestParam(value = "page", defaultValue = "0") int page) {
        return employeeService.getEmployeesByPage(page);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        employeeService.uploadFile(file);
    }

    @PostMapping("/report")
    public int createReport() throws IOException {
        return employeeService.createReport();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<ByteArrayResource> downloadFileById(@PathVariable int id) throws IOException {
        return employeeService.downloadFileById(id);
    }
}