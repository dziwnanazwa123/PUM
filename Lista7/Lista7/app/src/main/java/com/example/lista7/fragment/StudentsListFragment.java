package com.example.lista7.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lista7.R;
import com.example.lista7.databinding.StudentsListBinding;
import com.example.lista7.recyclerview.StudentComparator;
import com.example.lista7.recyclerview.StudentsListAdapter;
import com.example.lista7.viewmodel.StudentsListViewModel;

public class StudentsListFragment extends Fragment {
    private StudentsListViewModel viewModel;
    private StudentsListAdapter adapter;
    private StudentsListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = StudentsListBinding.inflate(inflater);
        viewModel = new ViewModelProvider(requireActivity()).get(StudentsListViewModel.class);
        adapter = new StudentsListAdapter(new StudentComparator(), position -> {
            Bundle args = new Bundle();
            args.putInt("position", position);

            Navigation.findNavController(requireView()).navigate(R.id.action_studentsListFragment_to_studentInfoFragment, args);
        });

        adapter.submitList(viewModel.getStudentsList());

        binding.rvList.setAdapter(adapter);
        binding.rvList.setLayoutManager(new LinearLayoutManager(requireActivity()));

        return binding.getRoot();
    }
}
