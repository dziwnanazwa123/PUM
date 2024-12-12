package com.example.lista3.model;

import java.util.List;

public class ExerciseList {
    private List<Exercise> exercises;
    private Subject subject;
    private Double grade;
    private String name;

    public ExerciseList(List<Exercise> exercises, Subject subject, Double grade, String name) {
        this.exercises = exercises;
        this.subject = subject;
        this.grade = grade;
        this.name = name;
    }

    public ExerciseList() {
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
