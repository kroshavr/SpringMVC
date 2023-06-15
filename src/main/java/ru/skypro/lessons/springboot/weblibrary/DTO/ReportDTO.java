package ru.skypro.lessons.springboot.weblibrary.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ReportDTO {

    private int id;
    private String departmentName;
    private int employeesQuantity;
    private int maxSalary;
    private int minSalary;
    private double avgSalary;

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
