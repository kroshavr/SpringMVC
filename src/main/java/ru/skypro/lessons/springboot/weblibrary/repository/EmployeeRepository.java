package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.weblibrary.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository {
    @Query(value = "SELECT * FROM employee",
            nativeQuery = true)
    List<Employee> getAllEmployees();

    @Query (value = "SELECT * FROM employee INNER JOIN position" +
            "ON employee.position_id=position.name " +
            "WHERE name = ?",
            nativeQuery = true)
    List<Employee> findByPositionName(String position);

    @Query(value = "SELECT * FROM employee " +
            "WHERE salary = (SELECT MAX(salary) " +
            "FROM employee)",
            nativeQuery = true)
    List<Employee> getEmployeesWithHighestSalary();

    Page<Employee> findAll(Pageable pageable);
}
