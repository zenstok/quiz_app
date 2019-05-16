package com.example.quiz_app.sqlite_db;


import com.example.quiz_app.sqlite_db.entities.NewQuizInstance;

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    static void initializeDb(AppDatabase db, NewQuizInstance newQuizInstance) {
        db.beginTransaction();
        try {
            db.newQuizInstanceDao().insertNewQuizInstance(newQuizInstance);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}