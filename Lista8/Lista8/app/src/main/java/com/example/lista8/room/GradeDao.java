package com.example.lista8.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lista8.entity.Grade;

import java.util.List;

@Dao
public interface GradeDao {
    @Query("SELECT * FROM grade_table ORDER BY grade DESC, subject ASC")
    LiveData<List<Grade>> getGrades();

    @Query("SELECT * FROM grade_table WHERE id = :id LIMIT 1")
    Grade getGradeById(Integer id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Grade grade);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Grade grade);

    @Delete
    void delete(Grade grade);
}
