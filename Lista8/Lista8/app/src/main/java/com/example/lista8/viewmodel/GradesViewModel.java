package com.example.lista8.viewmodel;

import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import androidx.lifecycle.LiveData;

import com.example.lista8.entity.Grade;
import com.example.lista8.repository.GradeRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GradesViewModel extends ViewModel {
    private final LiveData<List<Grade>> allGrades;
    private final LiveData<Double> meanGrade;

    @Inject
    public GradesViewModel(GradeRepository gradeRepository) {
        this.allGrades = gradeRepository.getAllGrades();
        this.meanGrade = Transformations.map(allGrades, grades -> {
            if (grades == null || grades.isEmpty()) {
                return 0.0; // Default mean when there are no grades
            }
            double sum = 0;
            for (Grade grade : grades) {
                sum += grade.getGrade(); // Assuming `getValue` returns a numeric value
            }
            return sum / grades.size();
        });
    }

    public LiveData<List<Grade>> getAllGrades() {
        return allGrades;
    }

    public LiveData<Double> getMean() {
        return meanGrade;
    }
}
