package com.example.lista8.fragment;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.lista8.R;
import com.example.lista8.databinding.FragmentEditBinding;
import com.example.lista8.entity.Grade;
import com.example.lista8.viewmodel.EditGradeViewModel;

import java.util.HashMap;
import java.util.Map;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FragmentEdit extends Fragment {
    private FragmentEditBinding binding;
    private final Map<Double, Boolean> allowedGrades = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditBinding.inflate(inflater, container, false);
        EditGradeViewModel viewModel = new ViewModelProvider(this).get(EditGradeViewModel.class);

        if (getArguments() != null) {
            final int position = getArguments().getInt("position", -1);

            if (position != -1) {
                viewModel.getAllGrades().observe(getViewLifecycleOwner(), grades -> {
                    Grade existingGrade = grades.get(position);

                    if (existingGrade != null) {
                        binding.subjectText.setText(existingGrade.getSubject());
                        binding.gradeText.setText(existingGrade.getGrade().toString());

                        binding.editGradeButton.setOnClickListener(v -> {
                            String newSubject = binding.subjectText.getText().toString();
                            Double newGrade = Double.valueOf(binding.gradeText.getText().toString());

                            if (!isDataValid(newSubject, newGrade)) return;

                            existingGrade.setGrade(newGrade);
                            existingGrade.setSubject(newSubject);

                            viewModel.update(existingGrade);

                            Navigation.findNavController(requireView()).navigate(R.id.action_fragmentEdit_to_fragmentGrades);
                        });

                        binding.deleteGradeButton.setOnClickListener(v -> {
                            viewModel.delete(existingGrade);
                            Navigation.findNavController(requireView()).navigate(R.id.action_fragmentEdit_to_fragmentGrades);
                        });
                    }
                });
            }

        }

        return binding.getRoot();
    }

    private Boolean isDataValid(String subject, Double grade) {
        return !subject.isEmpty() && !subject.isBlank() && allowedGrades.containsKey(grade);
    }
}
