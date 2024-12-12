package com.example.lista3.RecycleView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lista3.databinding.AssignmentsListItemBinding;
import com.example.lista3.model.ExerciseList;

import java.util.Locale;

public class AssignmentsListViewHolder extends RecyclerView.ViewHolder {
    private final AssignmentsListItemBinding binding;

    public AssignmentsListViewHolder(AssignmentsListItemBinding binding, OnItemClickListener onItemClickListener) {
        super(binding.getRoot());
        this.binding = binding;
        binding.getRoot().setOnClickListener(v ->
                onItemClickListener.onItemClick(getAdapterPosition()));
    }

    public void bind(ExerciseList exerciseList){
        binding.subjectTextView.setText(exerciseList.getSubject().getName());
        binding.listTextView.setText(exerciseList.getName());
        binding.taskCountTextView.setText(String.format(Locale.getDefault(), "Liczba zadań: %d", exerciseList.getExercises().size()));
        binding.gradeTextView.setText(String.format(Locale.getDefault(), "Ocena: %.1f",
                exerciseList.getGrade()));
    }
}
