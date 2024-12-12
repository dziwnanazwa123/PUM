package com.example.lista3;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.lista3.model.Exercise;
import com.example.lista3.model.ExerciseList;
import com.example.lista3.model.Grade;
import com.example.lista3.model.Subject;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private final List<ExerciseList> exerciseLists = new ArrayList<>();
    private final List<Grade> gradeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeExerciseList();
        initializeGrades();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            navController = NavHostFragment.findNavController(navHostFragment);
        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    public List<ExerciseList> getExerciseLists() {
        return exerciseLists;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    private void initializeExerciseList() {
        Random rand = new Random();

        List<Subject> subjects = List.of(
                new Subject("Wstęp do algebry"),
                new Subject("Programowanie urządzeń mobilnych"),
                new Subject("fizyka"),
                new Subject("Programowanie w C++"),
                new Subject("Matematyka")
        );
        List<Double> grades =  List.of(3.0, 3.5, 4.0, 4.5, 5.0);

        Map<Subject, Integer> subjectAssignemntsCountMap = new HashMap<>();

        for (int i = 0; i < 20; i++) {
            ExerciseList exerciseList = new ExerciseList();
            List<Exercise> exercises = new ArrayList<>();

            for (int ii = 0; ii < rand.nextInt(10) + 1; ii++) {
                exercises.add(new Exercise("lorem ipsum".repeat(rand.nextInt(5) + 1), rand.nextInt(11)));
            }

            exerciseList.setExercises(exercises);
            exerciseList.setGrade(grades.get(rand.nextInt(5)));

            Subject subject = subjects.get(rand.nextInt(5));

            subjectAssignemntsCountMap.merge(subject, 1, Integer::sum);

            exerciseList.setSubject(subject);

            exerciseList.setName("Lista " + subjectAssignemntsCountMap.get(subject));

            exerciseLists.add(exerciseList);
        }
    }

    private void initializeGrades() {
        Map<Subject, List<Double>> subjectGradesMap = new HashMap<>();

        for (ExerciseList exerciseList : exerciseLists) {
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
    }
}