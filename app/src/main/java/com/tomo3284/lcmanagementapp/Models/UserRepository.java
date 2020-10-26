package com.tomo3284.lcmanagementapp.Models;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;

public class UserRepository {
    // firebase database instance as a field
    // local sqlLite database as a filed

    private AppDatabase appDatabase;
    private final Executor executor;

    public UserRepository(Context context, Executor executor) {
        // todo : instantiate/get fields
        this.appDatabase = AppDatabase.getDatabase(context);
        this.executor = executor;
    }

    // call by UserViewModel
    public LiveData<User> getUser(String username) {
        refreshUser(username);
        return appDatabase.userDao().findUserByUsername(username);
    }

    private void refreshUser(String username) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                LiveData<User> liveUser = appDatabase.userDao().findUserByUsername(username);
                // user is not cached, so fetch from remote database
                if(liveUser == null){
                    // todo : fetch user, problems, from firebase database
                    // todo : might change the way little bit based on firebase database schema
                    User user = null;
                    List<Problem> problems = null;

                    // todo : check for errors

                    // update the local cached
                    appDatabase.userDao().insert(user);
                    appDatabase.problemDao().insertAll(user.getProblemList().getProblems());
                }
            }
        });
    }
}
