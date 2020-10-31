package com.tomo3284.lcmanagementapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {

    @PrimaryKey
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "email")
    private String email;

    @Ignore
    private ProblemList problemList;

    public User(String userName, String password, String email, ProblemList problemList) {
        this.username = userName;
        this.password = password;
        this.email = email;
        this.problemList = problemList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                "userName='" + username + '\'' +
                ", problemList=" + problemList +
                '}';
    }
}
