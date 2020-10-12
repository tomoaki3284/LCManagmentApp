package com.tomo3284.lcmanagementapp.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProblemList {
    public List<Problem> problems;

    public ProblemList() {
        this.problems = new ArrayList<>();
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public List<Problem> getDifficultySortedList() {
        Collections.sort(problems, (a,b) ->
            a.getDifficultyValue() - b.getDifficultyValue()
        );
        return problems;
    }

    public List<Problem> getProblemNumberSortedList() {
        Collections.sort(problems, (a,b) ->
                a.getProblemNumber() - b.getProblemNumber()
        );
        return problems;
    }

    public List<Problem> filterDifficulty(String difficulty) {
        List<Problem> filteredList = new ArrayList<>();
        for(Problem problem : problems){
            if (problem.getDifficulty().equals(difficulty)){
                filteredList.add(problem);
            }
        }
        return filteredList;
    }

    public void addProblem(Problem problem) {
        getProblems().add(problem);
    }
}
