package com.tomo3284.lcmanagementapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.tomo3284.lcmanagementapp.R;
import com.tomo3284.lcmanagementapp.fragments.IntroPage1Fragment;
import com.tomo3284.lcmanagementapp.fragments.IntroPage2Fragment;
import com.tomo3284.lcmanagementapp.fragments.IntroPage3Fragment;

public class IntroActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 3;

    private IntroActivity activity = this;
    private ImageView mBgImg;
    private ImageView mLogo;
    private TextView mTitleTV;
    private LottieAnimationView mEffectiveGif;
    private ViewPager mViewPager;
    private ScreenSlidePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        setupView();
    }

    private void setupView() {
        mBgImg = findViewById(R.id.bgImg);
        mLogo = findViewById(R.id.logo);
        mTitleTV = findViewById(R.id.textView9);
        mEffectiveGif = findViewById(R.id.lottie);
        // animation
        mBgImg.animate().translationY(-3000).setDuration(1000).setStartDelay(5000);
        mLogo.animate().translationY(2000).setDuration(1000).setStartDelay(5000);
        mTitleTV.animate().translationY(2000).setDuration(1000).setStartDelay(5000);
        mEffectiveGif.animate().translationY(2000).setDuration(1000).setStartDelay(5000);

        mViewPager = findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(activity, R.color.white));
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    IntroPage1Fragment introPage1Fragment = new IntroPage1Fragment();
                    return introPage1Fragment;
                case 1:
                    IntroPage2Fragment introPage2Fragment = new IntroPage2Fragment();
                    return introPage2Fragment;
                case 2:
                    IntroPage3Fragment introPage3Fragment = new IntroPage3Fragment();
                    return introPage3Fragment;
            }

            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}