package com.tomo3284.lcmanagementapp.Animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.transition.TransitionValues;
import androidx.transition.Visibility;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

public class CircularRevealTransition extends Visibility {

    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        int startRadius = 0;
        int endRadius = (int) Math.hypot(view.getWidth(), view.getHeight());
        Animator reveal = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, startRadius, endRadius);
        reveal.setDuration(1000);
        //make view invisible until animation actually starts
        view.setAlpha(0);
        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setAlpha(1);
            }
        });
        return reveal;
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        int endRadius = 0;
        int startRadius = (int) Math.hypot(view.getWidth(), view.getHeight());
        Animator reveal = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, startRadius, endRadius);
        reveal.setDuration(1000);
        return reveal;
    }
}
