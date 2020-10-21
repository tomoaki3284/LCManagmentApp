package com.tomo3284.lcmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.circularreveal.CircularRevealCompat;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;
import com.tomo3284.lcmanagementapp.Animation.CircularRevealTransition;
import com.tomo3284.lcmanagementapp.Models.Problem;
import com.tomo3284.lcmanagementapp.Models.ProblemList;
import com.tomo3284.lcmanagementapp.Models.User;
import com.tomo3284.lcmanagementapp.Models.UserViewModel;
import com.tomo3284.lcmanagementapp.fragments.ProfileFragment;
import com.tomo3284.lcmanagementapp.fragments.SolveFragment;
import com.tomo3284.lcmanagementapp.fragments.SolvedListFragment;
import com.tomo3284.lcmanagementapp.fragments.SolvingFragment;

public class MainActivity extends AppCompatActivity {

    private MainActivity mainActivity = this;

    // view related
    private SolveFragment mSolveFragment;
    private SolvedListFragment mSolvedListFragment;
    private ProfileFragment mProfileFragment;
    BottomNavigationView mBottomNavigationView;

    // variables
    private User mUser;
    private UserViewModel mUserVM;
    private int currentPageId = R.id.page_1;

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationListener = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // user is solving problem, shouldn't be able to use bottom navigation
            if (getTopFragmentTag().equals(SolvingFragment.TAG)) {
                Toast.makeText(mainActivity, "while solving problem, navigation is not active", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(item.getItemId() == currentPageId){
                return false;
            }

            currentPageId = item.getItemId();
            Fragment itemFragment = null;
            String tag = "";
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            
            switch (item.getItemId()) {
                case R.id.page_1: // solve fragment
                    mSolveFragment = new SolveFragment();
                    mSolveFragment.setParentActivity(mainActivity);
                    itemFragment = mSolveFragment;
                    tag = SolveFragment.TAG;
                    mBottomNavigationView.setBackgroundResource(R.color.leetcodeMainColor);
                    window.setStatusBarColor(ContextCompat.getColor(mainActivity, R.color.leetcodeMainColor));
                    break;

                case R.id.page_2: // solved list fragment
                    mSolvedListFragment = new SolvedListFragment();
                    mSolvedListFragment.setParentActivity(mainActivity);
                    itemFragment = mSolvedListFragment;
                    tag = SolvedListFragment.TAG;
                    mBottomNavigationView.setBackgroundResource(R.color.whitePink);
                    window.setStatusBarColor(ContextCompat.getColor(mainActivity, R.color.whitePink));
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
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
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

        loadUser();
        setupViewModel();

        setupFragment();
    }

    private void setupViewModel() {
        mUserVM = new ViewModelProvider(this).get(UserViewModel.class);
        mUserVM.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mUser = user;
            }
        });
        System.out.println(mUser);
        mUserVM.setUser(mUser);
        System.out.println(mUserVM.getUser().getValue());
    }

    private void loadUser() {
        // TODO: 2020/10/12 - load user from database/file
        // for now, it's just init user
        String username = "tomoaki3284";
        ProblemList problemList = new ProblemList();

        // randomly init solved problem list for test purposes
        String[] randomTitles = {"number theory", "rotate string", "rotate 2d array", "make 0's island"};
        String[] randomDifficulties = {"Easy", "Medium", "Hard"};
        for (int i=0; i<7; i++) {
            String title = randomTitles[(int) (Math.random() * randomTitles.length)];
            String difficulty = randomDifficulties[(int) (Math.random() * randomDifficulties.length)];
            int probNumber = (int) (Math.random() * 1234);
            Problem problem = new Problem(difficulty,title,probNumber);
            problem.setElapsedTimeMin(50);
            problem.setEstimatedTimeMin(30);
            problemList.addProblem(problem);
        }

        mUser = new User(username, problemList);
    }

    private void setupFragment() {
        if (mSolveFragment == null) {
            mSolveFragment = new SolveFragment();
            mSolveFragment.setParentActivity(mainActivity);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mSolveFragment, SolveFragment.TAG)
                .addToBackStack(SolveFragment.TAG)
                .commit();
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationListener);
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

    public void replaceTopFragment(Fragment replaceBy, String tag) {
        popFragmentFromBackStack();
        pushFragment(replaceBy, tag);
    }

    public void popFragmentFromBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentManager.popBackStackImmediate();// pop current top fragment

        // make the top fragment visible
        int top = fragmentManager.getBackStackEntryCount() - 1;
        if (top >= 0) {
            FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(top);
            Fragment currentFragment = fragmentManager.findFragmentByTag(backStackEntry.getName());
            currentFragment.getView().setVisibility(View.VISIBLE);
        }
    }

    private String getTopFragmentTag() {
        int topIndex = getSupportFragmentManager().getBackStackEntryCount() - 1;
        FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(topIndex);
        return backEntry.getName();
    }
}