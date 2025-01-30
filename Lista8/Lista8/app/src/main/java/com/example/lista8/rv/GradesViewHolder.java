package com.example.lista8.rv;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lista8.databinding.RvItemBinding;
import com.example.lista8.entity.Grade;

public class GradesViewHolder extends RecyclerView.ViewHolder {
    private final RvItemBinding binding;

    public GradesViewHolder(RvItemBinding binding, OnItemClickListener onItemClickListener) {
        super(binding.getRoot());
        this.binding = binding;
        binding.getRoot().setOnClickListener(view ->
                onItemClickListener.onItemClick(getAdapterPosition()));
    }

    public void bind(Grade grade) {
        binding.subjectTextView.setText(grade.getSubject());
        binding.gradeTextView.setText(grade.getGrade().toString());
    }
}
