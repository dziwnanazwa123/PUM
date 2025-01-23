package com.example.lista7.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.lista7.databinding.RvItemBinding;
import com.example.lista7.model.Student;

public class StudentsListAdapter extends ListAdapter<Student, StudentsListViewHolder> {
    private final OnItemClickListener onItemClickListener;

    public StudentsListAdapter(StudentComparator studentComparator, OnItemClickListener onItemClickListener) {
        super(studentComparator);
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public StudentsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentsListViewHolder(RvItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false),
                onItemClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsListViewHolder holder, int position) {
        Student item = getItem(position);
        holder.bind(item);
    }
}
