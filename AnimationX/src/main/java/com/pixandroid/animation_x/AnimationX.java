package com.pixandroid.animation_x;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.RecyclerView;

/********************************************
 * https://github.com/pixAndroid/AnimationX
 *     Created by DailyCoding on 24-Feb-23.  *
 ********************************************/


public class AnimationX {

    // Enhanced Interpolator Support
    public enum InterpolatorType {
        LINEAR, ACCELERATE, DECELERATE, ACCELERATE_DECELERATE,
        BOUNCE, OVERSHOOT, ANTICIPATE, ANTICIPATE_OVERSHOOT, CYCLE
    }
    
    public static Interpolator getInterpolator(InterpolatorType type) {
        switch (type) {
            case LINEAR: return new LinearInterpolator();
            case ACCELERATE: return new AccelerateInterpolator();
            case DECELERATE: return new DecelerateInterpolator();
            case ACCELERATE_DECELERATE: return new AccelerateDecelerateInterpolator();
            case BOUNCE: return new BounceInterpolator();
            case OVERSHOOT: return new OvershootInterpolator();
            case ANTICIPATE: return new AnticipateInterpolator();
            case ANTICIPATE_OVERSHOOT: return new AnticipateOvershootInterpolator();
            case CYCLE: return new CycleInterpolator(1.0f);
            default: return new LinearInterpolator();
        }
    }

    // Physics-Based Spring Animations (API 16+)
    public static void springAnimation(View view, DynamicAnimation.ViewProperty property, 
                                     float targetValue, float stiffness, float damping, 
                                     AnimationXListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            SpringAnimation springAnim = new SpringAnimation(view, property, targetValue);
            SpringForce springForce = new SpringForce(targetValue);
            springForce.setStiffness(stiffness);
            springForce.setDampingRatio(damping);
            springAnim.setSpring(springForce);
            
            if (listener != null) {
                springAnim.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                        listener.onAnimationCompleted();
                    }
                });
            }
            springAnim.start();
        }
    }

    // 3D Rotation Animations
    public static void rotateX(View view, float fromDegrees, float toDegrees, int duration, 
                              InterpolatorType interpolatorType, AnimationXListener listener) {
        ObjectAnimator rotateX = ObjectAnimator.ofFloat(view, "rotationX", fromDegrees, toDegrees);
        rotateX.setDuration(duration);
        rotateX.setInterpolator(getInterpolator(interpolatorType));
        if (listener != null) {
            rotateX.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationCompleted();
                }
            });
        }
        rotateX.start();
    }

    public static void rotateY(View view, float fromDegrees, float toDegrees, int duration, 
                              InterpolatorType interpolatorType, AnimationXListener listener) {
        ObjectAnimator rotateY = ObjectAnimator.ofFloat(view, "rotationY", fromDegrees, toDegrees);
        rotateY.setDuration(duration);
        rotateY.setInterpolator(getInterpolator(interpolatorType));
        if (listener != null) {
            rotateY.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationCompleted();
                }
            });
        }
        rotateY.start();
    }

    public static void rotateZ(View view, float fromDegrees, float toDegrees, int duration, 
                              InterpolatorType interpolatorType, AnimationXListener listener) {
        ObjectAnimator rotateZ = ObjectAnimator.ofFloat(view, "rotation", fromDegrees, toDegrees);
        rotateZ.setDuration(duration);
        rotateZ.setInterpolator(getInterpolator(interpolatorType));
        if (listener != null) {
            rotateZ.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationCompleted();
                }
            });
        }
        rotateZ.start();
    }

    // Enhanced ViewPropertyAnimator Support
    public static ViewPropertyAnimator modernFadeIn(View view, int duration, InterpolatorType interpolatorType) {
        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);
        return view.animate()
                .alpha(1f)
                .setDuration(duration)
                .setInterpolator(getInterpolator(interpolatorType));
    }

    public static ViewPropertyAnimator modernFadeOut(View view, int duration, InterpolatorType interpolatorType) {
        return view.animate()
                .alpha(0f)
                .setDuration(duration)
                .setInterpolator(getInterpolator(interpolatorType))
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(View.GONE);
                    }
                });
    }

    public static ViewPropertyAnimator modernScaleIn(View view, int duration, InterpolatorType interpolatorType) {
        view.setScaleX(0f);
        view.setScaleY(0f);
        view.setVisibility(View.VISIBLE);
        return view.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(duration)
                .setInterpolator(getInterpolator(interpolatorType));
    }

    public static ViewPropertyAnimator modernScaleOut(View view, int duration, InterpolatorType interpolatorType) {
        return view.animate()
                .scaleX(0f)
                .scaleY(0f)
                .setDuration(duration)
                .setInterpolator(getInterpolator(interpolatorType))
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(View.GONE);
                    }
                });
    }


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

    public static void click_effect_shake_left(Context context, View view, Animation.AnimationListener listener) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.shake_left);

        // Attach the listener if provided
        if (listener != null) {
            animation.setAnimationListener(listener);
        }

        // Start the animation
        view.startAnimation(animation);
    }

    public static void click_effect_shake_right(Context context, View view, Animation.AnimationListener listener) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.shake_right);

        // Attach the listener if provided
        if (listener != null) {
            animation.setAnimationListener(listener);
        }

        // Start the animation
        view.startAnimation(animation);
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

    public static void recyclerview_slide_in_left(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.slide_in_left_layout);
        recyclerView.setLayoutAnimation(controller);
        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
        recyclerView.scheduleLayoutAnimation();
    }

    public static void recyclerview_slide_in_right(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.slide_in_right_layout);
        recyclerView.setLayoutAnimation(controller);
        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
        recyclerView.scheduleLayoutAnimation();
    }

    public static void recyclerview_fade_in(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.fade_in_layout);
        recyclerView.setLayoutAnimation(controller);
        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
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

    public static void startBounceAnimationTwice(Context context, View targetView,  Animation.AnimationListener listener) {
        // Load the animation
        Animation scaleAnimation = AnimationUtils.loadAnimation(context, R.anim.bounce_twice_animation);

        // Set repeat count to make the animation run twice
        scaleAnimation.setRepeatCount(3); // (1 cycle + reverse) * 2 = 2 full cycles

        // Attach the listener if provided
        if (listener != null) {
            scaleAnimation.setAnimationListener(listener);
        }

        // Start the animation
        targetView.startAnimation(scaleAnimation);
    }

    public static void startVerticalBounceAnimation(Context context, View targetView, int animationResourceId, int repeatCount, Animation.AnimationListener listener) {
        // Load the animation
        Animation verticalAnimation = AnimationUtils.loadAnimation(context, animationResourceId);

        // Set repeat count dynamically
        verticalAnimation.setRepeatCount(repeatCount);

        // Attach the listener if provided
        if (listener != null) {
            verticalAnimation.setAnimationListener(listener);
        }

        // Start the animation
        targetView.startAnimation(verticalAnimation);
    }

    public static void startBounceAnimation(
            View targetView,
            float toXScale,
            float toYScale,
            int duration,
            int repeat_count,
            Animation.AnimationListener listener) {

        // Create a ScaleAnimation programmatically
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, toXScale, // From XScale to XScale
                1.0f, toYScale, // From YScale to YScale
                Animation.RELATIVE_TO_SELF, 0.5f, // PivotX at 50% of the view's width
                Animation.RELATIVE_TO_SELF, 0.5f  // PivotY at 50% of the view's height
        );

        // Set animation duration
        scaleAnimation.setDuration(duration);

        // Set repeat count and mode to reverse for smooth bouncing
        scaleAnimation.setRepeatCount(repeat_count); // (1 cycle + reverse) * 2 = 2 full cycles
        scaleAnimation.setRepeatMode(Animation.REVERSE);

        // Attach the listener if provided
        if (listener != null) {
            scaleAnimation.setAnimationListener(listener);
        }

        // Start the animation
        targetView.startAnimation(scaleAnimation);
    }

    // Morphing and Path Animations
    public static void morphWidth(View view, int fromWidth, int toWidth, int duration, 
                                 InterpolatorType interpolatorType, AnimationXListener listener) {
        ValueAnimator animator = ValueAnimator.ofInt(fromWidth, toWidth);
        animator.setDuration(duration);
        animator.setInterpolator(getInterpolator(interpolatorType));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                view.getLayoutParams().width = animatedValue;
                view.requestLayout();
            }
        });
        
        if (listener != null) {
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationCompleted();
                }
            });
        }
        animator.start();
    }

    public static void morphHeight(View view, int fromHeight, int toHeight, int duration, 
                                  InterpolatorType interpolatorType, AnimationXListener listener) {
        ValueAnimator animator = ValueAnimator.ofInt(fromHeight, toHeight);
        animator.setDuration(duration);
        animator.setInterpolator(getInterpolator(interpolatorType));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                view.getLayoutParams().height = animatedValue;
                view.requestLayout();
            }
        });
        
        if (listener != null) {
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationCompleted();
                }
            });
        }
        animator.start();
    }

    // Parallax Effect
    public static void parallaxTranslate(View view, float scrollOffset, float parallaxFactor) {
        view.setTranslationY(scrollOffset * parallaxFactor);
    }

    // Flip Animations
    public static void flipHorizontal(View view, int duration, AnimationXListener listener) {
        ObjectAnimator flipOut = ObjectAnimator.ofFloat(view, "rotationY", 0f, 90f);
        flipOut.setDuration(duration / 2);
        
        ObjectAnimator flipIn = ObjectAnimator.ofFloat(view, "rotationY", -90f, 0f);
        flipIn.setDuration(duration / 2);
        
        AnimatorSet flipSet = new AnimatorSet();
        flipSet.play(flipOut).before(flipIn);
        
        if (listener != null) {
            flipSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationCompleted();
                }
            });
        }
        flipSet.start();
    }

    public static void flipVertical(View view, int duration, AnimationXListener listener) {
        ObjectAnimator flipOut = ObjectAnimator.ofFloat(view, "rotationX", 0f, 90f);
        flipOut.setDuration(duration / 2);
        
        ObjectAnimator flipIn = ObjectAnimator.ofFloat(view, "rotationX", -90f, 0f);
        flipIn.setDuration(duration / 2);
        
        AnimatorSet flipSet = new AnimatorSet();
        flipSet.play(flipOut).before(flipIn);
        
        if (listener != null) {
            flipSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationCompleted();
                }
            });
        }
        flipSet.start();
    }

    // Reveal Animations
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void circularReveal(View view, int centerX, int centerY, float startRadius, 
                                    float endRadius, int duration, AnimationXListener listener) {
        Animator circularReveal = android.view.ViewAnimationUtils.createCircularReveal(
                view, centerX, centerY, startRadius, endRadius);
        circularReveal.setDuration(duration);
        
        if (listener != null) {
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationCompleted();
                }
            });
        }
        circularReveal.start();
    }

    // Enhanced Animation Chaining
    public static class AnimationChain {
        private AnimatorSet animatorSet;
        private Animator.AnimatorListener finalListener;

        public AnimationChain() {
            this.animatorSet = new AnimatorSet();
        }

        public AnimationChain addFadeIn(View view, int duration, InterpolatorType interpolatorType) {
            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            fadeIn.setDuration(duration);
            fadeIn.setInterpolator(getInterpolator(interpolatorType));
            this.animatorSet.play(fadeIn);
            return this;
        }

        public AnimationChain addScaleUp(View view, int duration, InterpolatorType interpolatorType) {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f);
            scaleX.setDuration(duration);
            scaleY.setDuration(duration);
            scaleX.setInterpolator(getInterpolator(interpolatorType));
            scaleY.setInterpolator(getInterpolator(interpolatorType));
            this.animatorSet.play(scaleX).with(scaleY);
            return this;
        }

        public AnimationChain addRotation(View view, float degrees, int duration, InterpolatorType interpolatorType) {
            ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", 0f, degrees);
            rotation.setDuration(duration);
            rotation.setInterpolator(getInterpolator(interpolatorType));
            this.animatorSet.play(rotation);
            return this;
        }

        public AnimationChain setListener(AnimationXListener listener) {
            this.finalListener = new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (listener != null) listener.onAnimationCompleted();
                }
            };
            return this;
        }

        public void start() {
            if (finalListener != null) {
                animatorSet.addListener(finalListener);
            }
            animatorSet.start();
        }
    }

    // Enhanced text animations
    public static void typewriterEffect(TextView textView, String text, int delayPerChar, 
                                       AnimationXListener listener) {
        textView.setText("");
        textView.setVisibility(View.VISIBLE);
        
        ValueAnimator animator = ValueAnimator.ofInt(0, text.length());
        animator.setDuration(text.length() * delayPerChar);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                textView.setText(text.substring(0, progress));
            }
        });
        
        if (listener != null) {
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationCompleted();
                }
            });
        }
        animator.start();
    }

    // Wave Effect
    public static void waveAnimation(View view, int duration, float amplitude, AnimationXListener listener) {
        ObjectAnimator wave = ObjectAnimator.ofFloat(view, "translationY", 
            0f, amplitude, -amplitude, amplitude/2, 0f);
        wave.setDuration(duration);
        wave.setInterpolator(new BounceInterpolator());
        
        if (listener != null) {
            wave.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationCompleted();
                }
            });
        }
        wave.start();
    }

    // Elastic Scale Animation
    public static void elasticScale(View view, float scale, int duration, AnimationXListener listener) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, scale, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, scale, 1f);
        
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(scaleX).with(scaleY);
        animSet.setDuration(duration);
        animSet.setInterpolator(new OvershootInterpolator());
        
        if (listener != null) {
            animSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationCompleted();
                }
            });
        }
        animSet.start();
    }

    // XML-based convenience animations
    public static void wiggleAnimation(Context context, View view, AnimationXListener listener) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.wiggle);
        if (listener != null) {
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}
                @Override
                public void onAnimationEnd(Animation animation) {
                    listener.onAnimationCompleted();
                }
                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
        }
        view.startAnimation(animation);
    }

    public static void elasticScaleXML(Context context, View view, AnimationXListener listener) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.elastic_scale);
        if (listener != null) {
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}
                @Override
                public void onAnimationEnd(Animation animation) {
                    listener.onAnimationCompleted();
                }
                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
        }
        view.startAnimation(animation);
    }

    public static void floatingEffect(Context context, View view) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.floating_effect);
        view.startAnimation(animation);
    }

    // Improved versions of existing methods with interpolator support
    public static void fade_in(View view, int duration, InterpolatorType interpolatorType, AnimationXListener listener) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setRepeatMode(Animation.RESTART);
        alphaAnimation.setInterpolator(getInterpolator(interpolatorType));
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
                if (listener != null)
                    listener.onAnimationCompleted();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    public static void fade_out(View view, int duration, InterpolatorType interpolatorType, AnimationXListener listener) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setRepeatMode(Animation.RESTART);
        alphaAnimation.setInterpolator(getInterpolator(interpolatorType));
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
                if (listener != null) {
                    listener.onAnimationCompleted();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    public static void alphaAnimation(View view, float from_alpha, float to_alpha, int duration, 
                                    InterpolatorType interpolatorType, AnimationXListener listener) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(from_alpha, to_alpha);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setRepeatMode(Animation.RESTART);
        alphaAnimation.setInterpolator(getInterpolator(interpolatorType));
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null)
                    listener.onAnimationCompleted();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}
