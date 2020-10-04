package com.tomo3284.lcmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tomo3284.lcmanagementapp.fragments.ProfileFragment;
import com.tomo3284.lcmanagementapp.fragments.SolveFragment;
import com.tomo3284.lcmanagementapp.fragments.SolvedListFragment;

public class MainActivity extends AppCompatActivity {

    private MainActivity mainActivity = this;

    private SolveFragment mSolveFragment;
    private SolvedListFragment mSolvedListFragment;
    private ProfileFragment mProfileFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationListener= new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment itemFragment = null;
            
            switch (item.getItemId()) {
                case R.id.page_1: // solve fragment
                    if (mSolveFragment == null) {
                        mSolveFragment = new SolveFragment();
                        mSolveFragment.setParentActivity(mainActivity);
                    }
                    itemFragment = mSolveFragment;
                    break;

                case R.id.page_2: // solved list fragment
                    if (mSolvedListFragment == null) {
                        mSolvedListFragment = new SolvedListFragment();
                        mSolvedListFragment.setParentActivity(mainActivity);
                    }
                    itemFragment = mSolvedListFragment;
                    break;

                case R.id.page_3: // profile fragment
                    if (mProfileFragment == null) {
                        mProfileFragment = new ProfileFragment();
                        mProfileFragment.setParentActivity(mainActivity);
                    }
                    itemFragment = mProfileFragment;
                    break;
                    
                default:
                    itemFragment = mSolveFragment;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, itemFragment).commit();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mSolveFragment == null) {
            mSolveFragment = new SolveFragment();
            mSolveFragment.setParentActivity(mainActivity);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mSolveFragment).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationListener);
    }
}