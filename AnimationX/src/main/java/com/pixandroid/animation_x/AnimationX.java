package com.pixandroid.animation_x;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

/********************************************
 *     Created by DailyCoding on 24-Feb-23.  *
 ********************************************/

public class AnimationX {

    public static void click_effect_shake_left(Context context, View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake_left));
    }

    public static void click_effect_scale_up(Context context, View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.click_scale));
    }

    public static void color_change_effect(Context context, View view, int colorFrom, int colorTo, int duration) {

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(duration); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                view.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    public static void pulse_effect(Context context, View view) {
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        scaleDown.setDuration(310);

        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

        scaleDown.start();
    }


    public static void recyclerview_fall_down_item(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public static void recyclerview_scale_up_item(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_scale);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }


    public static void down(Context context, View view, int visible) {
        Animation bottomUp = AnimationUtils.loadAnimation(context,
                R.anim.down);
        view.startAnimation(bottomUp);
        view.setVisibility(visible);
    }

    public static void up(Context context, View view, int visible) {
        Animation bottomUp = AnimationUtils.loadAnimation(context,
                R.anim.up);
        view.startAnimation(bottomUp);
        view.setVisibility(visible);
    }


    public static void fade_out(View view, int duration) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(alphaAnimation);
    }

    public static void smoothProgressAnimation(ProgressBar pb, int progressTo, long anim_duration)
    {
        //TODO : HOW TO SET MAX VALUE IN YOUR PROGRESS BAR?
        //TODO : pb.setMax(yourMaxValue * 100);
        ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", pb.getProgress(), progressTo * 100);
        animation.setDuration(anim_duration);
        animation.setAutoCancel( true);
        animation.setInterpolator( new DecelerateInterpolator());
        animation.start();
    }

}
