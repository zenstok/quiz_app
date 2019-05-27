package com.example.quiz_app.sqlite_db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.quiz_app.sqlite_db.entities.Question;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question question);

    @Update
    void update(Question question);

    @Delete
    void delete(Question question);

    @Query("SELECT * FROm Question")
    List<Question> getAllQuestions();

    @Query("SELECT * FROM Question WHERE quiz_id=:quizId")
    List<Question> findQuestionsForQuiz(final int quizId);
}
