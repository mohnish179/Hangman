package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView play;
    TextView info;
    Handler handler=new Handler();
    Animation animation,animation2;
    Intent intent;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        myDialog = new Dialog(new ContextThemeWrapper(this,R.style.DialogSlideAnim2));
        play=findViewById(R.id.play);
        info=findViewById(R.id.info);
        animation= AnimationUtils.loadAnimation(this,R.anim.zoomout);
        animation2=AnimationUtils.loadAnimation(this,R.anim.zoomin);
        intent=new Intent(this,gameplay.class);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(intent);
              overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.startAnimation(animation);
                showPopup();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        info.startAnimation(animation2);
                    }
                },500);

            }
        });

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    public void showPopup()
    {
        myDialog.setContentView(R.layout.pop_up_2);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
        myDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setWindowAnimations(R.style.DialogAnimation2);
    }
}