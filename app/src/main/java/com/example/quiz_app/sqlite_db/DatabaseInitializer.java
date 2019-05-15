package com.example.quiz_app.sqlite_db;


import com.example.quiz_app.sqlite_db.entities.NewQuizInstance;

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    static void initializeDb(AppDatabase db) {
        db.beginTransaction();
        try {
            db.newQuizInstanceDao().insertNewQuizInstance(new NewQuizInstance(1,"test1",10,10,10,"1234a"));
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}