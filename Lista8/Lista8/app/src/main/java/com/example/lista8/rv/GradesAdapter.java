package com.example.lista8.rv;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.lista8.databinding.RvItemBinding;
import com.example.lista8.entity.Grade;

public class GradesAdapter extends ListAdapter<Grade, GradesViewHolder> {
    private final OnItemClickListener onItemClickListener;

    public GradesAdapter(GradesComparator gradeComparator, OnItemClickListener onItemClickListener) {
        super(gradeComparator);
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public GradesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GradesViewHolder(RvItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false),
                onItemClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull GradesViewHolder holder, int position) {
        Grade item = getItem(position);
        holder.bind(item);
    }
}
