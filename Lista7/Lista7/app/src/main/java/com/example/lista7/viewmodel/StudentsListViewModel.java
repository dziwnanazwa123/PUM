package com.example.lista7.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.lista7.DataProvider;
import com.example.lista7.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentsListViewModel extends ViewModel {
    private final List<Student> studentsList = new ArrayList<>();

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public StudentsListViewModel() {
        reinitialize();
    }

    private void reinitialize() {
        studentsList.clear();
        studentsList.addAll(DataProvider.getStudents());
        studentsList.sort(Comparator.comparing(Student::getLastName));
    }
}
