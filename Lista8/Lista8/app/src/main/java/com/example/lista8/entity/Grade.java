package com.example.lista8.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "grade_table")
public class Grade {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String subject;
    private Double grade;

    public Grade(String subject, double grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade1 = (Grade) o;
        return Objects.equals(id, grade1.id) && Objects.equals(subject, grade1.subject) && Objects.equals(grade, grade1.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, grade);
    }

    public double getValue() {
        return 0;
    }
}
