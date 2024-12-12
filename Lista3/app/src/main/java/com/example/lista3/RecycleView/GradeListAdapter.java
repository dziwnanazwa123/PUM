package com.example.lista3.RecycleView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lista3.databinding.GradeListItemBinding;
import com.example.lista3.model.Grade;

import java.util.List;

public class GradeListAdapter extends RecyclerView.Adapter<GradeListViewHolder>  {
    private final List<Grade> gradeList;

    public GradeListAdapter(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    @NonNull
    @Override
    public GradeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GradeListViewHolder(GradeListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull GradeListViewHolder holder, int position) {
        Grade currentItem = gradeList.get(position);
        holder.bind(currentItem);
    }

    @Override
    public int getItemCount() {
        return gradeList.size();
    }
}
