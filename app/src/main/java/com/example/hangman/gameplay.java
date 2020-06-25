package com.example.hangman;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class gameplay extends AppCompatActivity implements View.OnClickListener {
    Button back;
KonfettiView viewKonfetti;
int round;
int hint_no=0;
Button hint;
    Dialog myDialog;
    TextView title,descriptiion;
    Animation mydialog;
    Button next;
   Animation[] animation=new Animation[26];
   Animation[] animation2=new Animation[26];
    Intent intent;
    int y2;
int r=0;
TextView topic;

    Handler handler=new Handler();

    Character letter;
    int i;
    Intent intent2;
    CustomVIew mc;
    TextView rond;
    TextView[] t=new TextView[26];
    private static final String TAG = "gameplay";
    ArrayList<String> animals=new ArrayList<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        back=findViewById(R.id.back);
        hint=findViewById(R.id.hint);
        mc=findViewById(R.id.c1);
        rond=findViewById(R.id.round);
        topic=findViewById(R.id.topic);
        myDialog = new Dialog(new ContextThemeWrapper(this,R.style.DialogSlideAnim));


        intent=new Intent(this,MainActivity.class);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);


            }
        });
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                hint_no++;
            get_hint();
            }
        });

        Log.d(TAG, "onCreate: score"+GameState.INSTANCE.getScore());

        for(i=0;i<26;i++)
        {
            String b="tv"+(i+1);
            t[i]=findViewById(getResources().getIdentifier(b,"id",getPackageName()));
            t[i].setOnClickListener(this);
            animation[i]=AnimationUtils.loadAnimation(this,R.anim.zoomout);
            animation2[i]=AnimationUtils.loadAnimation(this,R.anim.zoomin);
        }//for




        if(GameState.INSTANCE.get_round()!=1) {
            editor.putInt("round", GameState.INSTANCE.get_round());
            editor.apply();
        }
        else {
            GameState.INSTANCE.change_round(sharedPreferences.getInt("round",1));
        }

        rond.setText("Round: "+sharedPreferences.getInt("round",1));


        int[] num2=new int[35];
        num2=GameState.INSTANCE.getScore();
        for(int i=0;i<3;i++)
        {
            Log.d(TAG, "checking value of num2 "+num2[i]+ "  ;;;; i value is "+i);
        }

Random random=new Random();
r=random.nextInt(35);
        Log.d(TAG, "what is r initial value "+r);


    while (num2[r] == 1) {

        r=random.nextInt(35);
        Log.d(TAG, "amount of times loop is run "+r);

    }

        Log.d(TAG, "checking wether r is not repeating "+r);
GameState.INSTANCE.addScore(r);

get_animals();

if(r/5==0)
{
    topic.setText("Animals");
}
else if(r/5==1)
{
    topic.setText("Countries");
}
else if(r/5==2)
{
    topic.setText("Movies");
}
else if(r/5==3)
{
    topic.setText("Food");
}
else if(r/5==4)
{
    topic.setText("Sports");
}
else if(r/5==5)
{
    topic.setText("Colors");
}
else if(r/5==6)
{
    topic.setText("Flowers");
}




mc.get_info(animals.get(r));






    }//on_create

    @Override
    public void onClick(View v) {

            switch(v.getId())
            {
                case R.id.tv1:
                  letter_anim(0);
                        Log.d(TAG, "onClick: animation of A");
                   letter='A';


                    Log.d(TAG, "onClick: A");

                   mc.draw_letter(letter);

                   break;

                case R.id.tv2:
                  letter_anim(1);
                    letter='B';
                    mc.draw_letter(letter);

                    break;
                case R.id.tv3:
                    letter_anim(2);
                    letter='C';
                    mc.draw_letter(letter);
                     break;
                case R.id.tv4:
                    letter_anim(3);
                    letter='D';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv5:
                    letter_anim(4);
                    letter='E';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv6:
                    letter_anim(5);
                    letter='F';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv7:
                    letter_anim(6);
                    letter='G';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv8:
                    letter_anim(7);
                    letter='H';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv9:
                    letter_anim(8);
                    letter='I';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv10:
                    letter_anim(9);
                    letter='J';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv11:
                    letter_anim(10);
                    letter='K';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv12:
                    letter_anim(11);
                    letter='L';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv13:
                    letter_anim(12);
                    letter='M';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv14:
                    letter_anim(13);
                    letter='N';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv15:
                    letter_anim(14);
                    letter='O';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv16:
                    letter_anim(15);
                    letter='P';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv17:
                    letter_anim(16);
                    letter='Q';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv18:
                    letter_anim(17);
                    letter='R';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv19:
                    letter_anim(18);
                    letter='S';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv20:
                    letter_anim(19);
                    letter='T';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv21:
                    letter_anim(20);
                    letter='U';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv22:
                    letter_anim(21);
                    letter='V';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv23:
                    letter_anim(22);
                    letter='W';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv24:
                    letter_anim(23);
                    letter='X';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv25:
                    letter_anim(24);
                    letter='Y';
                    mc.draw_letter(letter);
                    break;
                case R.id.tv26:
                    letter_anim(25);
                    letter='Z';
                    mc.draw_letter(letter);
                    break;
            }


            if(mc.mistake_ctr==6)
            {
                handler.postDelayed(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void run() {
                        ShowPopup();
                    }
                },1000);

            }
        Log.d(TAG, "onClick:for correct");
            if(mc.check==1)
            {
                handler.postDelayed(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void run() {
                        ShowPopup();

                    }
                },1000);
            }

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
    public void get_animals()
    {
//ANIMALS
        animals.add("ELEPHANT");
        animals.add("MONKEY");
        animals.add("PENGUIN");
        animals.add("DOLPHIN");
        animals.add("TIGER");
       //COUNTRIES
        animals.add("TURKEY");
        animals.add("INDIA");
        animals.add("UAE");
        animals.add("ENGLAND");
        animals.add("FRANCE");
        //MOVIES
        animals.add("UP");
        animals.add("JOKER");
        animals.add("TITANIC");
        animals.add("AVATAR");
        animals.add("TENET");
        //FOOD
        animals.add("BARBEQUE");
        animals.add("SUSHI");
        animals.add("BAGUETTE");
        animals.add("BIRYANI");
        animals.add("NACHOS");
        //SPORT
        animals.add("CRICKET");
        animals.add("FOOTBALL");
        animals.add("HOCKEY");
        animals.add("GOLF");
        animals.add("BOXING");
        //COLOR
        animals.add("ORANGE");
        animals.add("PINK");
        animals.add("MAGENTA");
        animals.add("WHITE");
        animals.add("YELLOW");
        //FLOWER
        animals.add("TULIP");
        animals.add("HIBISCUS");
        animals.add("LOTUS");
        animals.add("DAISY");
        animals.add("ROSE");

    }



    public void letter_anim(int y){

    y2=y;
        t[y2].setClickable(false);
        t[y].startAnimation(animation[y2]);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                t[y2].startAnimation(animation2[y2]);
            }
        },200);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                t[y2].setTextColor(Color.parseColor("#9ADADADA"));

            }
        },400);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void ShowPopup() {

        myDialog.setContentView(R.layout.pop_up_1);

        title=(TextView)myDialog.findViewById(R.id.title);
        next=myDialog.findViewById(R.id.next);
        ImageView imageView= myDialog.findViewById(R.id.imageview);
        descriptiion=(TextView)myDialog.findViewById(R.id.correct_answer);
        if(mc.mistake_ctr==6)
        {
            title.setText("Oops ! You Lost ");
            imageView.setImageResource(R.drawable.sad3);
            descriptiion.setText(animals.get(r));
            next.setBackgroundResource(R.drawable.back);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();

                    GameState.INSTANCE.clear();
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("round", 1);
                    editor.apply();

                 intent2=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent2);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                }
            });


        }
        else if(GameState.INSTANCE.get_round()==30)
        {
            title.setText("Game Completed!!!");
            title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            imageView.setImageResource(R.drawable.happy2);
            descriptiion.setText(animals.get(r));

            viewKonfetti=findViewById(R.id.viewKonfetti);

            viewKonfetti.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                    .addSizes(new Size(12, 5))
                    .setPosition(-50f, 1000F, -50f, 100F)
                    .streamFor(300, 5000L);

            next.setBackgroundResource(R.drawable.back);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();

                    GameState.INSTANCE.clear();
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("round", 1);
                    editor.apply();

                    intent2=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent2);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                }
            });

        }
        else if(mc.check==1)
        {
            title.setText("Congrats! You Win ");
            imageView.setImageResource(R.drawable.happy2);
            viewKonfetti=findViewById(R.id.viewKonfetti);

            viewKonfetti.build()
                    .addColors(Color.BLACK)
                    .setDirection(0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                    .addSizes(new Size(12, 5))
                    .setPosition(-50f, 1000F, -50f, 100F)
                    .streamFor(300, 5000L);

            descriptiion.setText(animals.get(r));
            GameState.INSTANCE.increase_round();
            next.setBackgroundResource(R.drawable.next2);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();

                    finish();

                    startActivity(getIntent());

                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }
            });
        }



        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
        myDialog.getWindow().setWindowAnimations(R.style.DialogAnimation);



    }

    public enum GameState{
        INSTANCE;

        private int[] num=new int[35];
        private int round=1;

        private GameState(){
            for(int i=0;i<10;i++)
            {
                num[i]=-1;
            }
        }

        public int[] getScore(){
            return num;
        }
        public void addScore(int score){
            num[score]=1;
        }
        public void clear()
        {
            for(int i=0;i<10;i++)
            {
                num[i]=-1;
            }
round=1;
        }

        public void increase_round()
        {
            round+=1;

        }
        public int get_round()
        {
           return round;
        }
public void change_round(int score)
{
    round=score;
}

    }
public void get_hint()
{
    String x;
    Character x2;
    int n_b;
    x=animals.get(r);

    n_b=x.length();
    if(n_b==2)
    { if(hint_no==1)
    {x2 = x.charAt(0);
        mc.draw_letter(x2);
        Toast.makeText(this,"No more Hints ",Toast.LENGTH_SHORT).show();}
        else
    {
        Toast.makeText(this,"No more Hints ",Toast.LENGTH_SHORT).show();}

    }
    
    else if(n_b==3)
    {
        if(hint_no==1) {
            x2 = x.charAt(1);
            mc.draw_letter(x2);
            Toast.makeText(this,"One more Hint Left ",Toast.LENGTH_SHORT).show();
        }
        else if(hint_no==2)
        {
            x2 = x.charAt(2);
            mc.draw_letter(x2);
            Toast.makeText(this,"No more Hints ",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"No more Hints ",Toast.LENGTH_SHORT).show();

        }
    }
    else
    {
        if(hint_no==1)
        {
            x2 = x.charAt(n_b-1);
            mc.draw_letter(x2);
            Toast.makeText(this,"Two more Hints Left",Toast.LENGTH_SHORT).show();
        }
        else if(hint_no==2)
        {
            x2 = x.charAt(0);
            mc.draw_letter(x2);
            Toast.makeText(this,"One more Hint Left ",Toast.LENGTH_SHORT).show();
        }
        else if(hint_no==3)
        {
            x2 = x.charAt(1);
            mc.draw_letter(x2);
            Toast.makeText(this,"No more Hints ",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"No more Hints ",Toast.LENGTH_SHORT).show();

        }
    }


}


}
