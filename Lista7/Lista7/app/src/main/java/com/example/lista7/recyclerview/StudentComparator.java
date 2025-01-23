package com.example.lista7.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.lista7.model.Student;

public class StudentComparator extends DiffUtil.ItemCallback<Student> {
    @Override
    public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
        return oldItem.equals(newItem);
    }
}
