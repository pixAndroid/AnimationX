package com.pixandroid.animation_x;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/********************************************
 *     Created by DailyCoding on 24-Feb-23.  *
 ********************************************/

public class AnimationX {

    public static void click_effect_shake_left(Context context, View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake_left));
    }

    public static void click_effect_shake_right(Context context, View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake_right));
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

    public static void text_color_change_effect(Context context, TextView textView, int colorFrom, int colorTo, int duration) {

        if (colorFrom <= 0) {
            colorFrom = textView.getCurrentTextColor();
        }

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(duration); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                textView.setTextColor((int) animator.getAnimatedValue());
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
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setRepeatMode(Animation.RESTART);
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void fade_in(View view, int duration) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setRepeatMode(Animation.RESTART);
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void smoothProgressAnimation(ProgressBar pb, int progressTo, long anim_duration) {
        //TODO : HOW TO SET MAX VALUE IN YOUR PROGRESS BAR?
        //TODO : pb.setMax(yourMaxValue * 100);
        ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", pb.getProgress(), progressTo * 100);
        animation.setDuration(anim_duration);
        animation.setAutoCancel( true);
        animation.setInterpolator( new DecelerateInterpolator());
        animation.start();
    }

    public static void bottomToTop(View view, int duration, int fromY, int toY) {
        TranslateAnimation animate = new TranslateAnimation(0,0,fromY,toY);
        animate.setDuration(duration);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);
    }

    public static void translate(View view, int duration, int fromX, int toX, int fromY, int toY) {
        TranslateAnimation animate = new TranslateAnimation(fromX,toX,fromY,toY);
        animate.setDuration(duration);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);
    }

    public static void digitCounter(int start_number, int end_number, final TextView textview) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start_number, end_number);
        valueAnimator.setDuration(1500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textview.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator.start();

    }

    public static void spin_and_move_vertical(View view) {
        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation a = new TranslateAnimation(
               0, 0,
               2000,0);
        a.setDuration(1000);

        RotateAnimation r = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        r.setStartOffset(1000);
        r.setDuration(1400);

        animationSet.addAnimation(r);
        animationSet.addAnimation(a);

        view.startAnimation(animationSet);
    }

    public static void spin_and_move_vertical(Context context, View view, int visible) {
        Animation bottomUp = AnimationUtils.loadAnimation(context,
                R.anim.translate_rotate_anim);
        view.startAnimation(bottomUp);
        view.setVisibility(visible);
    }

}
