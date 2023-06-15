package ru.skypro.lessons.springboot.weblibrary.repository;

import org.hibernate.mapping.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.weblibrary.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query(value = "SELECT p.name AS positionName, " +
            "COUNT(*) AS employeeCount, " +
            "MAX(e.salary) AS maxSalary, " +
            "MIN(e.salary) AS minSalary, " +
            "AVG(e.salary) AS averageSalary " +
            "FROM Employee e " +
            "JOIN Position p ON e.position_id = p.position_id " +
            "GROUP BY positionName",
            nativeQuery = true)
     List<ReportDTO> getReport();
}
