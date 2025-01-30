package com.example.lista8.repository;

import androidx.lifecycle.LiveData;

import com.example.lista8.entity.Grade;
import com.example.lista8.room.GradeDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class GradeRepository {
    private final GradeDao gradeDao;
    private final ExecutorService executorService;

    @Inject
    public GradeRepository(GradeDao gradeDao) {
        this.gradeDao = gradeDao;
        this.executorService = Executors.newSingleThreadExecutor(); // Create a single thread executor for background tasks
    }

    public LiveData<List<Grade>> getAllGrades() {
        return gradeDao.getGrades();
    }

    public Grade getGradeById(Integer id) {
        return gradeDao.getGradeById(id);
    }

    public void insert(Grade grade) {
        gradeDao.insert(grade);
    }

    public void update(Grade grade) {
        gradeDao.update(grade);
    }

    public void delete(Grade grade) {
        gradeDao.delete(grade);
    }
}
