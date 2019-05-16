package com.example.quiz_app.sqlite_db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.quiz_app.sqlite_db.entities.NewQuizInstance;

import java.util.List;

@Dao
public interface NewQuizInstanceDao {

    @Query("SELECT * FROM NewQuizInstance LIMIT 1")
    List<NewQuizInstance> getAllQuizInstances();

    @Insert
    void insertNewQuizInstance(NewQuizInstance newQuizInstance);

    @Update
    void updateNewQuizInstance(NewQuizInstance newQuizInstance);

    @Delete
    void deleteNewQuizInstance(NewQuizInstance newQuizInstance);
}
