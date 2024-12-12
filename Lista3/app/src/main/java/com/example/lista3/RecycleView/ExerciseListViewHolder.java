package com.example.lista3.RecycleView;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lista3.databinding.ExerciseListItemBinding;
import com.example.lista3.model.Exercise;

import java.util.Locale;

public class ExerciseListViewHolder  extends RecyclerView.ViewHolder {
    private final ExerciseListItemBinding binding;

    public ExerciseListViewHolder(ExerciseListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Exercise exercise, Integer exerciseCount) {
        binding.exerciseNameTextView.setText(String.format(Locale.getDefault(), "zadanie %d",
                exerciseCount));
        binding.pointsTextView.setText(String.format(Locale.getDefault(),"pkt: %d",
                exercise.getPoints()));
        binding.contentTextView.setText(exercise.getContent());
    }
}
