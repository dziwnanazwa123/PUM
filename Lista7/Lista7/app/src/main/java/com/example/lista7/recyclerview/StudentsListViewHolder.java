package com.example.lista7.recyclerview;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lista7.databinding.RvItemBinding;
import com.example.lista7.model.Student;

import java.util.Locale;

public class StudentsListViewHolder extends RecyclerView.ViewHolder {
    private final RvItemBinding binding;

    public StudentsListViewHolder(RvItemBinding binding, OnItemClickListener onItemClickListener) {
        super(binding.getRoot());
        this.binding = binding;
        binding.getRoot().setOnClickListener(view ->
                onItemClickListener.onItemClick(getAdapterPosition()));
    }

    public void bind(Student student) {
        binding.nameTextView.setText(String.format(Locale.getDefault(),
                "%s %s", student.getName(), student.getLastName()));
        binding.indexNumberView.setText(student.getIndexNumber());
    }
}
