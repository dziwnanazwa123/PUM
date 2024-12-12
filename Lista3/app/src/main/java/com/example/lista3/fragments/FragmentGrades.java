package com.example.lista3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lista3.MainActivity;
import com.example.lista3.RecycleView.GradeListAdapter;
import com.example.lista3.databinding.FragmentGradesBinding;
import com.example.lista3.model.Grade;

import java.util.ArrayList;
import java.util.List;

public class FragmentGrades extends Fragment {
    private FragmentGradesBinding binding;
    private List<Grade> gradeList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGradesBinding.inflate(inflater);

        MainActivity mainActivity = (MainActivity) requireActivity();
        gradeList = mainActivity.getGradeList();

        binding.gradesRecyclerView.setAdapter(new GradeListAdapter(gradeList));
        binding.gradesRecyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

        return binding.getRoot();
    }
}
