package com.pixandroid.animation_x;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
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
                txt_number.setTextColor(Color.BLACK);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AnimationX.text_color_change_effect(MainActivity.this, txt_number, 0, Color.RED, 500, null);
                    }
                }, 500);
                //AnimationX.spin_animation(img, 50, 100, null);

                switch (radio_group.getCheckedRadioButtonId()) {
                    case R.id.rb_click_effect_shake_left:
                        AnimationX.click_effect_shake_right(MainActivity.this, view);
                        break;
                    case R.id.rb_click_effect_scale_up:
                        AnimationX.tap_anim_fade(btn_apply, 500, new AnimationX.AnimationXListener() {
                            @Override
                            public void onAnimationCompleted() {
                                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                            }
                        });


                        break;
                    case R.id.rb_color_change_effect:
                        AnimationX.view_color_change_effect(MainActivity.this, view, 0, Color.GREEN, 1000, null);
                        break;
                    case R.id.rb_fade_out:
                        AnimationX.fade_in(view, 500, null);
                        break;



                }

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