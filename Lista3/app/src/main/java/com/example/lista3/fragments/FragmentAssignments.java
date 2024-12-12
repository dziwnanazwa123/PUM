package com.example.lista3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lista3.R;
import com.example.lista3.RecycleView.AssignmentsListAdapter;
import com.example.lista3.MainActivity;
import com.example.lista3.databinding.FragmentAssignmentsBinding;
import com.example.lista3.model.ExerciseList;

import java.util.ArrayList;
import java.util.List;

public class FragmentAssignments extends Fragment {
    private FragmentAssignmentsBinding binding;
    private List<ExerciseList> exerciseLists = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAssignmentsBinding.inflate(inflater);

        MainActivity mainActivity = (MainActivity) requireActivity();
        exerciseLists = mainActivity.getExerciseLists();

        binding.assignmentsRecyclerView.setAdapter(new AssignmentsListAdapter(
                exerciseLists,
                position -> {
                    Bundle args = new Bundle();
                    args.putInt("position", position);

                    Navigation.findNavController(requireView()).navigate(R.id.action_fragmentAssignments_to_fragmentExerciseList, args);
                }
        ));
        binding.assignmentsRecyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

        return binding.getRoot();
    }
}
