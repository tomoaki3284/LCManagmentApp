package com.tomo3284.lcmanagementapp.Models;

public class Problem {

    private String difficulty;
    private long estimateTimeMillis;
    private long elapsedTimeMillis;
    private String title;
    private int problemNumber;
    private String note;
    private boolean completed;

    public Problem(String difficulty, String title, int problemNumber) {
        this.difficulty = difficulty;
        this.title = title;
        this.problemNumber = problemNumber;
        this.note = "";
        this.completed = false;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getDifficultyValue() {
        switch (difficulty) {
            case "easy":
                return 1;

            case "medium":
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
