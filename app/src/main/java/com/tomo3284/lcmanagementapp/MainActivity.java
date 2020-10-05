package com.tomo3284.lcmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.circularreveal.CircularRevealCompat;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;
import com.tomo3284.lcmanagementapp.Animation.CircularRevealTransition;
import com.tomo3284.lcmanagementapp.fragments.ProfileFragment;
import com.tomo3284.lcmanagementapp.fragments.SolveFragment;
import com.tomo3284.lcmanagementapp.fragments.SolvedListFragment;
import com.tomo3284.lcmanagementapp.fragments.SolvingFragment;

public class MainActivity extends AppCompatActivity {

    private MainActivity mainActivity = this;

    private SolveFragment mSolveFragment;
    private SolvedListFragment mSolvedListFragment;
    private ProfileFragment mProfileFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationListener = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // user is solving problem, shouldn't be able to use bottom navigation
            if (getTopFragmentTag().equals(SolvingFragment.TAG)) {
                Toast.makeText(mainActivity, "while solving problem, navigation is not active", Toast.LENGTH_SHORT).show();
                return false;
            }

            Fragment itemFragment = null;
            String tag = "";
            
            switch (item.getItemId()) {
                case R.id.page_1: // solve fragment
                    mSolveFragment = new SolveFragment();
                    mSolveFragment.setParentActivity(mainActivity);
                    itemFragment = mSolveFragment;
                    tag = SolveFragment.TAG;
                    break;

                case R.id.page_2: // solved list fragment
                    mSolvedListFragment = new SolvedListFragment();
                    mSolvedListFragment.setParentActivity(mainActivity);
                    itemFragment = mSolvedListFragment;
                    tag = SolvedListFragment.TAG;
                    break;

                case R.id.page_3: // profile fragment
                    mProfileFragment = new ProfileFragment();
                    mProfileFragment.setParentActivity(mainActivity);
                    itemFragment = mProfileFragment;
                    tag = ProfileFragment.TAG;
                    break;
                    
                default:
                    itemFragment = mSolveFragment;
                    tag = SolveFragment.TAG;
            }

            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragment_container, itemFragment, tag)
                    .addToBackStack(tag)
                    .commit();

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
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mSolveFragment, SolveFragment.TAG)
                .addToBackStack(SolveFragment.TAG)
                .commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationListener);
    }

    public void pushFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //when loading new fragment to the BackStack, make current top stack fragment hide
        int top = fragmentManager.getBackStackEntryCount() - 1;
        if (top >= 0) {
            FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(top);
            Fragment currentFragment = fragmentManager.findFragmentByTag(backStackEntry.getName());
            currentFragment.getView().clearAnimation();
            currentFragment.getView().setVisibility(View.GONE);
        }

        fragmentTransaction.add(R.id.fragment_container, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    private String getTopFragmentTag() {
        int topIndex = getSupportFragmentManager().getBackStackEntryCount() - 1;
        FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(topIndex);
        return backEntry.getName();
    }
}