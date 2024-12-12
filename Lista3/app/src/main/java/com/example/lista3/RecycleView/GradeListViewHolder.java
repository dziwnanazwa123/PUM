package com.example.lista3.RecycleView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lista3.databinding.GradeListItemBinding;
import com.example.lista3.model.Grade;

import java.util.Locale;

public class GradeListViewHolder extends RecyclerView.ViewHolder {
    private final GradeListItemBinding binding;

    public GradeListViewHolder(GradeListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Grade grade) {
        binding.subjectTextView.setText(grade.getSubject().getName());
        binding.gradeMeanTextView.setText(String.format(Locale.getDefault(), "Średnia: %.1f",
                grade.getGrade()));
        binding.gradesCountTextView.setText(String.format(Locale.getDefault(), "Liczba list: %d",
                grade.getAssignmentsCount()));
    }
}
