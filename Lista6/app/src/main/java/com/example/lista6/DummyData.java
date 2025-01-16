package com.example.lista6;

import com.example.lista6.model.Exercise;
import com.example.lista6.model.ExerciseList;
import com.example.lista6.model.Grade;
import com.example.lista6.model.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class DummyData {
    public DummyData() {
    }

    public List<ExerciseList> initializeExerciseList() {
        List<ExerciseList> exerciseLists = new ArrayList<>();

        Random rand = new Random();

        List<Subject> subjects = List.of(
                new Subject("Matematyka"),
                new Subject("PUM"),
                new Subject("Fizyka"),
                new Subject("Elektronika"),
                new Subject("Algorytmy")
        );
        List<Double> grades =  List.of(3.0, 3.5, 4.0, 4.5, 5.0);

        Map<Subject, Integer> subjectAssignemntsCountMap = new HashMap<>();

        for (int i = 0; i < 20; i++) {
            ExerciseList exerciseList = new ExerciseList();
            List<Exercise> exercises = new ArrayList<>();

            for (int ii = 0; ii < rand.nextInt(10) + 1; ii++) {
                exercises.add(new Exercise("lorem ipsum ".repeat(rand.nextInt(5) + 1), rand.nextInt(11)));
            }

            exerciseList.setExercises(exercises);
            exerciseList.setGrade(grades.get(rand.nextInt(5)));

            Subject subject = subjects.get(rand.nextInt(5));

            subjectAssignemntsCountMap.merge(subject, 1, Integer::sum);

            exerciseList.setSubject(subject);

            exerciseList.setName("Lista " + subjectAssignemntsCountMap.get(subject));

            exerciseLists.add(exerciseList);
        }

        return exerciseLists;
    }

    public List<Grade> initializeGradeList() {
        List<Grade> gradeList = new ArrayList<>();
        Map<Subject, List<Double>> subjectGradesMap = new HashMap<>();

        for (ExerciseList exerciseList : initializeExerciseList()) {
            Subject subject = exerciseList.getSubject();
            Double grade = exerciseList.getGrade();

            subjectGradesMap.computeIfAbsent(subject, k -> new ArrayList<>()).add(grade);
        }

        for (Map.Entry<Subject, List<Double>> entry : subjectGradesMap.entrySet()) {
            Subject subject = entry.getKey();
            List<Double> grades = entry.getValue();
            int assignmentsCount = grades.size();

            double averageGrade = grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

            Grade grade = new Grade(subject, averageGrade, assignmentsCount);
            gradeList.add(grade);
        }

        return gradeList;
    }
}