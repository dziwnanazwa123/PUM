package com.example.lista8.di;

import android.content.Context;

import androidx.room.Room;

import com.example.lista8.room.GradeDao;
import com.example.lista8.room.GradeDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class GradeDatabaseModule {

    @Provides
    @Singleton
    public GradeDatabase provideGradeDatabase(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                GradeDatabase.class,
                "grade_database"
        ).build();
    }

    @Provides
    public GradeDao provideGradeDao(GradeDatabase database) {
        return database.gradeDao();
    }
}