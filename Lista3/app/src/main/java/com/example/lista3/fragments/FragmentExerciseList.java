package com.example.lista3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lista3.MainActivity;
import com.example.lista3.RecycleView.ExerciseListAdapter;
import com.example.lista3.databinding.FragmentExerciseListBinding;
import com.example.lista3.model.Exercise;
import com.example.lista3.model.ExerciseList;

import java.util.List;
import java.util.Locale;

public class FragmentExerciseList extends Fragment {
    private FragmentExerciseListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExerciseListBinding.inflate(inflater);

        if (getArguments() != null) {
            int id = getArguments().getInt("position", -1);

            if (id != -1) {
                MainActivity mainActivity = (MainActivity) requireActivity();
                List<ExerciseList> exerciseLists = mainActivity.getExerciseLists();
                ExerciseList currentExerciseList = exerciseLists.get(id);

                List<Exercise> exercises = currentExerciseList.getExercises();

                binding.exercisesRecyclerView.setAdapter(new ExerciseListAdapter(exercises));
                binding.exercisesRecyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
                binding.subjectTextView.setText(String.format(Locale.getDefault(), "%s%n%s",
                        currentExerciseList.getSubject().getName(),
                        currentExerciseList.getName()));
            }
        }

        return binding.getRoot();
    }
}
