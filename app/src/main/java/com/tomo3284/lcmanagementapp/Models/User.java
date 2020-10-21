package com.tomo3284.lcmanagementapp.Models;

import java.io.Serializable;

public class User implements Serializable {

    private String userName;
    private ProblemList problemList;

    public User(String userName, ProblemList problemList) {
        this.userName = userName;
        this.problemList = problemList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ProblemList getProblemList() {
        return problemList;
    }

    public void setProblemList(ProblemList problemList) {
        this.problemList = problemList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", problemList=" + problemList +
                '}';
    }
}
