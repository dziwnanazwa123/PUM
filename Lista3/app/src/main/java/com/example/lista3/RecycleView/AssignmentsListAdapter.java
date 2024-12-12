package com.example.lista3.RecycleView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lista3.databinding.AssignmentsListItemBinding;
import com.example.lista3.model.ExerciseList;

import java.util.List;

public class AssignmentsListAdapter extends RecyclerView.Adapter<AssignmentsListViewHolder> {
    private final List<ExerciseList> exerciseLists;
    private final OnItemClickListener onItemClickListener;

    public AssignmentsListAdapter(List<ExerciseList> exerciseLists, OnItemClickListener onItemClickListener) {
        this.exerciseLists = exerciseLists;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AssignmentsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AssignmentsListViewHolder(
                AssignmentsListItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false),
                onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentsListViewHolder holder, int position) {
        ExerciseList currentItem = exerciseLists.get(position);
        holder.bind(currentItem);
    }

    @Override
    public int getItemCount() {
        return exerciseLists.size();
    }
}
