package com.pixandroid.animation_x;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RadioGroup radio_group = findViewById(R.id.radio_group);
        Button btn_reset = findViewById(R.id.btn_reset);
        Button btn_apply = findViewById(R.id.btn_apply);
        progress_bar = findViewById(R.id.progress_bar);
        TextView txt_number = findViewById(R.id.txt_number);


        AnimationX.dig

        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (radio_group.getCheckedRadioButtonId()) {
                    case R.id.rb_click_effect_shake_left:
                        AnimationX.click_effect_shake_left(MainActivity.this, view);
                        break;
                    case R.id.rb_click_effect_scale_up:
                        AnimationX.click_effect_scale_up(MainActivity.this, view);
                        break;
                    case R.id.rb_color_change_effect:
                        AnimationX.color_change_effect(MainActivity.this, view, Color.RED, Color.GREEN, 1000);
                        break;
                    case R.id.rb_fade_out:
                        AnimationX.fade_out(view, 2000);
                        break;



                }

            }
        });

        int yourMaxValue = 120000;
        progress_bar.setMax(yourMaxValue * 100);


        CountDownTimer countDownTimer = new CountDownTimer(yourMaxValue, 5000) {
            @Override
            public void onTick(long l) {
                int progress = (int) (yourMaxValue - l);
                AnimationX.smoothProgressAnimation(progress_bar, progress, 7000);
            }

            @Override
            public void onFinish() {
                AnimationX.smoothProgressAnimation(progress_bar, yourMaxValue, 7000);
            }
        }.start();



    }
}