package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
private final List<Employee> employeeList;
    public EmployeeRepositoryImpl(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    @Override
    public void addEmployee(List<EmployeeDTO> employees) {

    }


    @Override
    public void editEmployee(EmployeeDTO id, EmployeeDTO employee) {
        id.setName(employee.getName());
        id.setSalary(employee.getSalary());
    }



    @Override
    public Iterable saveAll(Iterable entities) {
        return null;
    }

    @Override
    public Iterable findAllById(Iterable iterable) {
        return null;
    }

    @Override
    public <S extends Employee> S save(S entity) {
        return null;
    }

    @Override
    public Optional<Employee> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Employee> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Employee entity) {

    }

    @Override
    public void deleteAllById(Iterable iterable) {
    }

    @Override
    public void deleteAll(Iterable entities) {
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public List<Employee> findByPositionName(String positionName) {
        return null;
    }

    @Override
    public List<Employee> getEmployeesWithHighestSalary() {
        return null;
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return null;
    }
}