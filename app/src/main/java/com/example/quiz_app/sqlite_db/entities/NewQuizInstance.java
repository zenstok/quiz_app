package com.example.quiz_app.sqlite_db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "NewQuizInstance")
public class NewQuizInstance {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "time")
    private int time;

    @ColumnInfo(name = "score")
    private int score;

    @ColumnInfo(name = "attemps")
    private int attemps;

    @ColumnInfo(name = "pin")
    private String pin;

    public NewQuizInstance(int id, String name, int time, int score, int attemps, String pin) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.score = score;
        this.attemps = attemps;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAttemps() {
        return attemps;
    }

    public void setAttemps(int attemps) {
        this.attemps = attemps;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
