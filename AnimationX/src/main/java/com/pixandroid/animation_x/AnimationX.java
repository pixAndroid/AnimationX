package com.pixandroid.animation_x;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/********************************************
 * https://github.com/pixAndroid/AnimationX
 *     Created by DailyCoding on 24-Feb-23.  *
 ********************************************/


public class AnimationX {


    public static void tap_anim_fade(View view, int duration, AnimationXListener listener) {
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
                if (listener != null)
                    listener.onAnimationCompleted();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void click_effect_shake_left(Context context, View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake_left));
    }

    public static void click_effect_shake_right(Context context, View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake_right));
    }

    public static void click_effect_scale_up(Context context, View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.click_scale));
    }

    public static void view_color_change_effect(Context context, View view, int colorFrom,
                                                int colorTo, int duration, AnimationXListener listener) {

        if (colorFrom == 0) {
            colorFrom = getBackgroundColor(view);
        }

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(duration); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                view.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });

        colorAnimation.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(@NonNull Animator animation, boolean isReverse) {
                super.onAnimationStart(animation, isReverse);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (listener != null)
                    listener.onAnimationCompleted();
            }
        });

        colorAnimation.start();
    }

    public static int getBackgroundColor(View view) {
        int color = Color.TRANSPARENT;
        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();

        return color;
    }

    public static void text_color_change_effect(Context context,
                                                TextView textView,
                                                int colorFrom,
                                                int colorTo,
                                                int duration,
                                                AnimationXListener listener) {

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

        colorAnimation.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(@NonNull Animator animation, boolean isReverse) {
                super.onAnimationStart(animation, isReverse);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (listener != null)
                    listener.onAnimationCompleted();
            }
        });

        colorAnimation.start();
    }



    public static void pulse_effect(Context context, View view, AnimationXListener listener) {
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        scaleDown.setDuration(310);

        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

        scaleDown.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(@NonNull Animator animation, boolean isReverse) {
                super.onAnimationStart(animation, isReverse);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (listener != null)
                    listener.onAnimationCompleted();
            }
        });


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


    public static void alphaAnimation(View view, float from_alpha, float to_alpha,  int duration, AnimationXListener listener) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(from_alpha, to_alpha);
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
                if (listener != null)
                    listener.onAnimationCompleted();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public interface AnimationXListener {
        void onAnimationCompleted();
    }


    public static void fade_out(View view, int duration, AnimationXListener listener) {
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
                if (listener != null) {
                    listener.onAnimationCompleted();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void fade_in(View view, int duration, AnimationXListener listener) {
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
                if (listener != null)
                    listener.onAnimationCompleted();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void smoothProgressAnimation(ProgressBar pb, int progressTo, long anim_duration, AnimationXListener listener) {
        //TODO : HOW TO SET MAX VALUE IN YOUR PROGRESS BAR?
        //TODO : pb.setMax(yourMaxValue * 100);
        ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", pb.getProgress(), progressTo * 100);
        animation.setDuration(anim_duration);
        animation.setAutoCancel( true);
        animation.setInterpolator( new DecelerateInterpolator());
        animation.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(@NonNull Animator animation, boolean isReverse) {
                super.onAnimationStart(animation, isReverse);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (listener != null)
                    listener.onAnimationCompleted();
            }
        });

        animation.start();
    }

    public static void vertical_move(View view, int duration, int fromY, int toY, AnimationXListener listener) {
        TranslateAnimation animate = new TranslateAnimation(0,0,fromY,toY);
        animate.setDuration(duration);
        animate.setFillAfter(true);

        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onAnimationCompleted();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);
    }

    public static void horizontal_move(View view, int duration, int fromX, int toX, AnimationXListener listener) {
        TranslateAnimation animate = new TranslateAnimation(fromX,toX,0,0);
        animate.setDuration(duration);
        animate.setFillAfter(true);

        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onAnimationCompleted();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);
    }

    public static void translate(View view, int duration, int fromX, int toX, int fromY, int toY, AnimationXListener listener) {
        TranslateAnimation animate = new TranslateAnimation(fromX,toX,fromY,toY);
        animate.setDuration(duration);
        animate.setFillAfter(true);
        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onAnimationCompleted();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);
    }

    public static void digitCounter(int start_number, int end_number, final TextView textview, AnimationXListener listener) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start_number, end_number);
        valueAnimator.setDuration(1500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textview.setText(valueAnimator.getAnimatedValue().toString());
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(@NonNull Animator animation, boolean isReverse) {
                super.onAnimationStart(animation, isReverse);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (listener != null)
                    listener.onAnimationCompleted();
            }
        });


        valueAnimator.start();

    }

    public static void spin_and_move_vertical(View view, int fromY, int toY, int duration, AnimationXListener listener) {


        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation a = new TranslateAnimation(
               0, 0,
               fromY,toY);
        a.setDuration((long) (duration/1.5));

        RotateAnimation r = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        r.setStartOffset(1000);
        r.setDuration(duration);

        animationSet.addAnimation(r);
        animationSet.addAnimation(a);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onAnimationCompleted();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(animationSet);
    }


    public static void spin_animation(View view, int duration, int repeat_count, AnimationXListener listener) {
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setRepeatCount(repeat_count);
        animationSet.addAnimation(rotateAnimation);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onAnimationCompleted();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(animationSet);
    }

    public static void shake_view(Context mContext, View view){
        Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
        view.startAnimation(shake);
    }

    public static void error_animation(Context mContext, View view){
        Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shake);
        view.startAnimation(shake);
    }

    public static void shakeLeft(View view, Context context) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake_left));
    }


    public static void splash_spin_and_move_vertical(View view, int duration, AnimationXListener listener) {
        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation a = new TranslateAnimation(
                0, 0,
                2000,0);
        a.setDuration(duration);

        RotateAnimation r = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // r.setStartOffset(1000);
        r.setDuration(1400);

        animationSet.addAnimation(r);
        animationSet.addAnimation(a);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onAnimationCompleted();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(animationSet);
    }

    public static void bottomToTop(View view, int duration, int fromY, int toY) {
        TranslateAnimation animate = new TranslateAnimation(0,0,fromY,toY);
        animate.setDuration(duration);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);
    }


}
