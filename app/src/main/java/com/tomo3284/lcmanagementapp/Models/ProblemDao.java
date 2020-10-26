package com.tomo3284.lcmanagementapp.Models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProblemDao {

    @Insert
    void insert(Problem problem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Problem> problems);

    @Update
    void update(Problem problem);

    @Delete
    void delete(Problem problem);

    @Query("SELECT * FROM problems")
    List<Problem> getAllProblems();

    @Query("SELECT * FROM problems WHERE username=:username")
    List<Problem> findProblemsByUsername(String username);
}
