package com.pixandroid.animation_x;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RadioGroup radio_group = findViewById(R.id.radio_group);
        Button btn_reset = findViewById(R.id.btn_reset);
        Button btn_apply = findViewById(R.id.btn_apply);


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





    }
}