package com.example.lista7.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.lista7.databinding.StudentInfoBinding;
import com.example.lista7.model.Student;
import com.example.lista7.viewmodel.StudentsListViewModel;

import java.util.Locale;

public class StudentInfoFragment extends Fragment {
    private StudentsListViewModel viewModel;
    private StudentInfoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = StudentInfoBinding.inflate(inflater);
        viewModel = new ViewModelProvider(requireActivity()).get(StudentsListViewModel.class);

        if (getArguments() != null) {
            Log.d("debug", "1");
            int id = getArguments().getInt("position", -1);

            if (id != -1) {
                Log.d("debug", "2");
                Student student = viewModel.getStudentsList().get(id);

                binding.nameTextView.setText(String.format(Locale.getDefault(), "Imie i nazwisko: %s %s",
                        student.getName(), student.getLastName()));
                binding.indexNumberTextView.setText(String.format(Locale.getDefault(), "Numer indeksu: %s",
                        student.getIndexNumber()));
                binding.gradesMeanTextView.setText(String.format(Locale.getDefault(), "Średnia ocen: %.2f",
                        student.getGradesMean()));
                binding.academicYearTextView.setText(String.format(Locale.getDefault(), "Rok: %d",
                        student.getAcademicYear()));
            }
        }

        return binding.getRoot();
    }
}
