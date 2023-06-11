package ru.skypro.lessons.springboot.weblibrary.DTO;

import lombok.Getter;
import lombok.Setter;
import ru.skypro.lessons.springboot.weblibrary.entity.Report;

import javax.persistence.*;
@Getter
@Setter

public class ReportDTO extends Report {

    int id;
    String departmentName;
    int employeesQuantity;
    int maxSalary;
    int minSalary;
    double avgSalary;

    public ReportDTO(String departmentName, int employeesQuantity, int maxSalary, int minSalary, double avgSalary) {
        this.departmentName = departmentName;
        this.employeesQuantity = employeesQuantity;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.avgSalary = avgSalary;
    }

    public ReportDTO() {

    }
}
