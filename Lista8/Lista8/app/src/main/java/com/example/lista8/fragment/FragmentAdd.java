package com.example.lista8.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.lista8.R;
import com.example.lista8.databinding.FragmentAddBinding;
import com.example.lista8.entity.Grade;
import com.example.lista8.viewmodel.AddGradeViewModel;
import com.example.lista8.viewmodel.GradesViewModel;

import java.util.HashMap;
import java.util.Map;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FragmentAdd extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAddBinding binding = FragmentAddBinding.inflate(inflater, container, false);
        AddGradeViewModel viewModel = new ViewModelProvider(this).get(AddGradeViewModel.class);

        binding.addGradeButton.setOnClickListener(v -> {
            String subject = binding.subjectText.getText().toString();
            if (subject.isEmpty() ||subject.isBlank()) return;

            Map<Double, Boolean> allowedGrades = new HashMap<>();
            for (double g = 2.; g <= 5.; g += 0.5) {
                allowedGrades.put(g, true);
            }

            Double grade = Double.valueOf(binding.gradeText.getText().toString());
            if (!allowedGrades.containsKey(grade)) return;

            Grade newGrade = new Grade(subject, grade);
            viewModel.insert(newGrade);

            Navigation.findNavController(requireView()).navigate(R.id.action_fragmentAdd_to_fragmentGrades);
        });

        return binding.getRoot();
    }
}
