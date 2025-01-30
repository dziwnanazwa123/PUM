package com.example.lista8.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.lista8.entity.Grade;
import com.example.lista8.repository.GradeRepository;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EditGradeViewModel extends ViewModel {
    private final GradeRepository gradeRepository;

    @Inject
    public EditGradeViewModel(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public LiveData<List<Grade>> getAllGrades() {
        return gradeRepository.getAllGrades();
    }

    public void update(Grade grade) {
        AppExecutors.getInstance().diskIO().execute(() -> gradeRepository.update(grade));
    }

    public void delete(Grade grade) {
        AppExecutors.getInstance().diskIO().execute(() -> gradeRepository.delete(grade));
    }


}
