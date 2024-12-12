package com.example.lista3.RecycleView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lista3.databinding.ExerciseListItemBinding;
import com.example.lista3.model.Exercise;

import java.util.List;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListViewHolder> {
    private final List<Exercise> exerciseList;
    private Integer exerciseCount = 0;

    public ExerciseListAdapter(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExerciseListViewHolder(ExerciseListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseListViewHolder holder, int position) {
        exerciseCount++;
        Exercise currentItem = exerciseList.get(position);
        holder.bind(currentItem, exerciseCount);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
