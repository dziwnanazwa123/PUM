package com.example.lista8.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lista8.entity.Grade;

@Database(entities = {Grade.class}, version = 1, exportSchema = false)
public abstract class GradeDatabase extends RoomDatabase {
    public abstract GradeDao gradeDao();
}