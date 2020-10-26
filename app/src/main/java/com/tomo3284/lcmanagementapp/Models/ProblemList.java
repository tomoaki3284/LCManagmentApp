package com.tomo3284.lcmanagementapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static androidx.room.ForeignKey.CASCADE;


public class ProblemList implements Serializable {

    private String username;

    private List<Problem> problems;

    public ProblemList() {
        this.problems = new ArrayList<>();
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public void sortByDifficulty(boolean isAscending) {
        Collections.sort(problems, (a,b) -> {
            if(!isAscending){
                Problem temp = a;
                a = b;
                b = temp;
            }
            return a.getDifficultyValue() - b.getDifficultyValue();
        });
    }

    public void sortByProblemNumber(boolean isAscending) {
        Collections.sort(problems, (a,b) -> {
            if(!isAscending){
                Problem temp = a;
                a = b;
                b = temp;
            }
            return a.getProblemNumber() - b.getProblemNumber();
        });
    }

    /**
     * sort by title, problem number, difficulty
     *
     * @param isAscending
     */
    public void sortByTitle(boolean isAscending) {
        Collections.sort(problems, (a,b) -> {
            if(!isAscending){
                Problem temp = a;
                a = b;
                b = temp;
            }
            if(a.getTitle().compareTo(b.getTitle()) == 0){
                if(a.getProblemNumber() == b.getProblemNumber()){
                    return a.getDifficulty().compareTo(b.getDifficulty());
                }
                return a.getProblemNumber() - b.getProblemNumber();
            }
            return a.getTitle().compareTo(b.getTitle());
        });
    }

    public void sortByCompleteness() {
        Collections.sort(problems, (a,b) -> {
            return Boolean.compare(a.isCompleted(), b.isCompleted());
        });
    }

    public ProblemList filterDifficulty(String difficulty) {
        List<Problem> filteredList = new ArrayList<>();
        for(Problem problem : problems){
            if (problem.getDifficulty().equals(difficulty)){
                filteredList.add(problem);
            }
        }
        return this;
    }

    public void addProblem(Problem problem) {
        getProblems().add(problem);
    }
}
