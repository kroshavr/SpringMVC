package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.getAllEmployees();
        return employees.stream()
                .map(EmployeeDTO ::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }
    @Override
    public void addEmployee(List<EmployeeDTO> employees) {
        employeeRepository.save(employees);
    }

    @Override
    public void editEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(employeeDTO);
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
}