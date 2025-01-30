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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lista8.R;
import com.example.lista8.databinding.FragmentGradesBinding;
import com.example.lista8.rv.GradesAdapter;
import com.example.lista8.rv.GradesComparator;
import com.example.lista8.viewmodel.GradesViewModel;

import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FragmentGrades extends Fragment {

    private GradesViewModel viewModel; // Corrected ViewModel
    private FragmentGradesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGradesBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(GradesViewModel.class);

        GradesAdapter adapter = new GradesAdapter(new GradesComparator(), position -> {
            Bundle args = new Bundle();
            args.putInt("position", position);

            Navigation.findNavController(requireView()).navigate(R.id.action_fragmentGrades_to_fragmentEdit, args);
        });

        binding.rvList.setAdapter(adapter);
        binding.rvList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvList.setItemAnimator(null);

        viewModel.getAllGrades().observe(getViewLifecycleOwner(), adapter::submitList);

        viewModel.getMean().observe(getViewLifecycleOwner(), mean -> {
            binding.gradesMeanTextView.setText(String.format(Locale.getDefault(), "%.2f", mean));
        });

        binding.addNewGradeButton.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_fragmentGrades_to_fragmentAdd);
        });

        return binding.getRoot();
    }
}
