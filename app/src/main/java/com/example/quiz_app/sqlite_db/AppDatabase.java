package com.example.quiz_app.sqlite_db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.quiz_app.sqlite_db.dao.NewQuizInstanceDao;
import com.example.quiz_app.sqlite_db.dao.QuestionDao;
import com.example.quiz_app.sqlite_db.entities.NewQuizInstance;
import com.example.quiz_app.sqlite_db.entities.Question;

@Database(entities = {NewQuizInstance.class, Question.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract NewQuizInstanceDao newQuizInstanceDao();
    public abstract QuestionDao questionDao();

    static final String DATABASE_NAME = "quiz_app_db-db";

    public static AppDatabase getAppDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }

}
