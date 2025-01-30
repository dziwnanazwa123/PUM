package com.example.lista8.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.lista8.entity.Grade;
import com.example.lista8.repository.GradeRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddGradeViewModel extends ViewModel {
    private final GradeRepository gradeRepository;

    @Inject
    public AddGradeViewModel(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public void insert(Grade grade) {
        AppExecutors.getInstance().diskIO().execute(() -> gradeRepository.insert(grade));
    }
}
