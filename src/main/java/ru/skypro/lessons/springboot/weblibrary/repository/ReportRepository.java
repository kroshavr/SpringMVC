package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.weblibrary.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary.entity.Report;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Integer> {
    @Query(value = "SELECT new ru.skypro.lessons.springboot.weblibrary.entity." +
            "Report (p.name, COUNT(e), MAX(e.salary), MIN(e.salary), AVG(e.salary))" +
            "FROM Employee e join fetch Position p WHERE e.position = p GROUP BY p.name",
            nativeQuery = true)
    List<ReportDTO> getReport();
}
