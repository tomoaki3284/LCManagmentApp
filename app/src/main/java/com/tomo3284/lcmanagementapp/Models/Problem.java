package com.tomo3284.lcmanagementapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "problems", foreignKeys = @ForeignKey(entity = User.class,
                                                        parentColumns = "username",
                                                        childColumns = "username",
                                                        onDelete = CASCADE))
public class Problem implements Serializable {

    // foreign key
    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "problem_number")
    private int problemNumber;

    @ColumnInfo(name = "difficulty")
    private String difficulty;

    @ColumnInfo(name = "estimate_time_millis")
    private long estimateTimeMillis;

    @ColumnInfo(name = "elapsed_time_millis")
    private long elapsedTimeMillis;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "note")
    private String note;

    @ColumnInfo(name = "completed")
    private boolean completed;

    public Problem(String difficulty, String title, int problemNumber, String username) {
        this.difficulty = difficulty;
        this.title = title;
        this.problemNumber = problemNumber;
        this.note = "";
        this.completed = false;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getDifficultyValue() {
        switch (difficulty) {
            case "Easy":
                return 1;

            case "Medium":
                return 2;

            default:
                return 3;
        }
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getEstimateTimeMin() {
        return (int) (estimateTimeMillis / 1000 / 60);
    }

    public int getElapsedTimeMin() {
        return (int) (elapsedTimeMillis / 1000 / 60);
    }

    public long getEstimateTimeMillis() {
        return estimateTimeMillis;
    }

    public void setEstimateTimeMillis(long estimateTimeMillis) {
        this.estimateTimeMillis = estimateTimeMillis;
    }

    public long getElapsedTimeMillis() {
        return elapsedTimeMillis;
    }

    public void setElapsedTimeMillis(long elapsedTimeMillis) {
        this.elapsedTimeMillis = elapsedTimeMillis;
    }

    public void setElapsedTimeMin(int min) {
        int milli = min * 60 * 1000;
        elapsedTimeMillis = milli;
    }

    public void setEstimatedTimeMin(int min) {
        int milli = min * 60 * 1000;
        estimateTimeMillis = milli;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProblemNumber() {
        return problemNumber;
    }

    public void setProblemNumber(int problemNumber) {
        this.problemNumber = problemNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
