package com.example.lista7.model;

import java.util.Objects;

public class Student {
    private String indexNumber;
    private String name;
    private String lastName;
    private Double gradesMean;
    private Integer academicYear;

    public Student() {}

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getGradesMean() {
        return gradesMean;
    }

    public void setGradesMean(Double gradesMean) {
        this.gradesMean = gradesMean;
    }

    public Integer getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(Integer academicYear) {
        this.academicYear = academicYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(indexNumber, student.indexNumber) && Objects.equals(name, student.name) && Objects.equals(lastName, student.lastName) && Objects.equals(gradesMean, student.gradesMean) && Objects.equals(academicYear, student.academicYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexNumber, name, lastName, gradesMean, academicYear);
    }
}
