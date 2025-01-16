package com.example.lista6.model;

public class Grade {
    private Subject subject;
    private Double grade;
    private Integer assignmentsCount;

    public Grade(Subject subject, Double grade, Integer assignmentsCount) {
        this.subject = subject;
        this.grade = grade;
        this.assignmentsCount = assignmentsCount;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Integer getAssignmentsCount() {
        return assignmentsCount;
    }

    public void setAssignmentsCount(Integer assignmentsCount) {
        this.assignmentsCount = assignmentsCount;
    }
}