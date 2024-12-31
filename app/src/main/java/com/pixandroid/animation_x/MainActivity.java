package com.pixandroid.animation_x;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar progress_bar;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RadioGroup radio_group = findViewById(R.id.radio_group);

        TextView btn_apply = findViewById(R.id.btn_apply);
        progress_bar = findViewById(R.id.progress_bar);
        img = findViewById(R.id.img);
        TextView txt_number = findViewById(R.id.txt_number);




        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationX.digitCounter(0, 486, txt_number, null);

                AnimationX.startBounceAnimationTwice(MainActivity.this,
                        btn_apply, new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

            }
        });

        int yourMaxValue = 30000;
        progress_bar.setMax(yourMaxValue * 100);


        CountDownTimer countDownTimer = new CountDownTimer(yourMaxValue, 5000) {
            @Override
            public void onTick(long l) {
                int progress = (int) (yourMaxValue - l);
                AnimationX.smoothProgressAnimation(progress_bar, progress, 7000, null);
            }

            @Override
            public void onFinish() {
                AnimationX.smoothProgressAnimation(progress_bar, yourMaxValue, 7000, null);
            }
        }.start();



    }
}