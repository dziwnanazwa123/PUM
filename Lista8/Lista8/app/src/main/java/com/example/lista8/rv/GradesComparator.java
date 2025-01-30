package com.example.lista8.rv;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.lista8.entity.Grade;

public class GradesComparator extends DiffUtil.ItemCallback<Grade> {
    @Override
    public boolean areItemsTheSame(@NonNull Grade oldItem, @NonNull Grade newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Grade oldItem, @NonNull Grade newItem) {
        return oldItem.equals(newItem);
    }
}
