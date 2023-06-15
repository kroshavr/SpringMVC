package ru.skypro.lessons.springboot.weblibrary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String json;

    public Report(String json) {
        this.json = json;
    }

    public Report() {

    }
}
