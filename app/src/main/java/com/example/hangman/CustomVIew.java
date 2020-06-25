package com.example.hangman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class CustomVIew extends View {
Paint black;
Rect rect;

int crt_ans=0;
int anim_head=0;
int anim_body=10;
int anim_rleg=5;
int anim_lleg=5;
int anim_lhand=10;
int anim_rhand=10;
    Canvas mBitmapCanvas;
    Bitmap mDrawBitmap;
    int check=0;
int[]count=new int[4];
int mistake_ctr=0;
int count2=-1;
Character l;
int flag=0;
int flag2=0;
int flag3=0;
int no_of_blanks;
int i=0,i2=-1,i3=-1;
String recieve_word;
    private static final String TAG = "CustomVIew";


    public CustomVIew(Context context) {
        super(context);
init();
    }

    public CustomVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init()
    {
        black= new Paint(Paint.ANTI_ALIAS_FLAG);
        black.setColor(Color.BLACK);
        black.setTextSize(85);
        black.setStrokeWidth(10);
        rect=new Rect();
    }

public void get_info(String sent_word)
{
    recieve_word=sent_word;
    no_of_blanks=recieve_word.length();
    postInvalidate();
}


public void draw_letter(Character letter)
{

    i=0;
    count2=-1;
    l=letter;
    Log.d(TAG, "draw_letter:1 "+i);
while(i<no_of_blanks)
{
    Log.d(TAG, "draw_letter: "+i);
    if(recieve_word.charAt(i)==letter)
    {
        count2++;
        crt_ans++;
        if(crt_ans==no_of_blanks)
        {
            check=1;
            Log.d(TAG, "crt_ans check");
        }

        Log.d(TAG, "draw_letter: crt ans "+crt_ans);
        count[count2]=i;//getting the no of letters to be printed and their positions

    }
    i++;
}
if(count2==-1)
{
    mistake_ctr++;
    Log.d(TAG, "mistake_ctr "+mistake_ctr);
    postInvalidate();
}
if(count2==0)
{
    i=count[0];
    flag=1;
    postInvalidate();
}
if(count2==1)
{
    i=count[0];
    i2=count[1];
    flag2=1;
    Log.d(TAG, "draw_letter: accessing values " +i2);
postInvalidate();
}
if(count2==2)
{
    i=count[0];
    i2=count[1];
    i3=count[2];
    flag3=1;
    postInvalidate();

}

}




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x=180;

        if (mDrawBitmap == null) {
            mDrawBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            mBitmapCanvas = new Canvas(mDrawBitmap);
        }//initialising bitmap

        canvas.drawRect(300,1000,600,1010,black);
        canvas.drawRect(380,600,390 ,1000,black);
        canvas.drawRect(380,600,640,610,black);
        canvas.drawRect(640,600,650,680,black);
        if(mistake_ctr==1)
        {
            RectF rectF = new RectF(600, 680, 690, 770);
            canvas.drawArc(rectF, -90,anim_head, true, black);
            if(anim_head!=360) {
                anim_head+=20;
                postInvalidateDelayed(1);

            }
            if(anim_head==360)
            {
                mBitmapCanvas.drawArc(rectF, -90,360, true, black);
            }
        }
        else if(mistake_ctr==2)
        {
            canvas.drawRect(640,770,650,770+anim_body,black);
            if(anim_body!=130)
            {
                anim_body+=10;
                postInvalidateDelayed(1);
            }
            if(anim_body==130)
            {
                mBitmapCanvas.drawRect(640,770,650,900,black);
            }
        }
else if(mistake_ctr==3)
        {
            canvas.drawLine(645,900,645-anim_lleg,900+anim_lleg,black);
           if(anim_lleg!=45)
           {
               anim_lleg+=5;
               postInvalidateDelayed(1);
           }
           if(anim_lleg==45)
           {
               mBitmapCanvas.drawLine(645,900,600,945,black);
           }
        }
else if(mistake_ctr==4)
        {
            canvas.drawLine(645,900,645+anim_rleg,900+anim_rleg,black);
            if(anim_rleg!=45)
            {
                anim_rleg+=5;
                postInvalidateDelayed(1);
            }
            if(anim_rleg==45)
            {
                mBitmapCanvas.drawLine(645,900,690,945,black);

            }

        }
else if(mistake_ctr==5) {
            canvas.drawLine(645, 810, 645-anim_lhand, 810-anim_lhand, black);
            if(anim_lhand!=60)
            {
                anim_lhand+=5;
                postInvalidateDelayed(1);
            }
            if(anim_lhand==60)
            {
                mBitmapCanvas.drawLine(645, 810, 585, 750, black);
            }

        }
else if(mistake_ctr==6)
        {
            canvas.drawLine(645,810,645+anim_rhand,810-anim_rhand,black);
            if(anim_rhand!=60)
            {
                anim_rhand+=5;
                postInvalidateDelayed(1);
            }
            if(anim_rhand==60)
            {
                mBitmapCanvas.drawLine(645,810,705,750,black);


            }
        }




for(int j=0;j<no_of_blanks;j++)
{
    canvas.drawRect(x, 1150, x+50, 1160, black);
if(flag2==1) {
    if (j == i) {
        mBitmapCanvas.drawText(String.valueOf(l), x - 3, 1140, black);
        Log.d(TAG, "onDraw: i " + l);

    }
    if(j==i2)
    {
        mBitmapCanvas.drawText(String.valueOf(l), x - 3, 1140, black);
        Log.d(TAG, "onDraw: i2" + l);

    }
}
if(flag==1)
{
    if (j == i) {
        mBitmapCanvas.drawText(String.valueOf(l), x - 3, 1140, black);
        Log.d(TAG, "onDraw: i " + l);

    }
}
if(flag3==1)
{
    if (j == i) {
        mBitmapCanvas.drawText(String.valueOf(l), x - 3, 1140, black);
        Log.d(TAG, "onDraw: i " + l);

    }
    if(j==i2)
    {
        mBitmapCanvas.drawText(String.valueOf(l), x - 3, 1140, black);
        Log.d(TAG, "onDraw: i2" + l);

    }
    if(j==i3)
    {
        mBitmapCanvas.drawText(String.valueOf(l), x - 3, 1140, black);
        Log.d(TAG, "onDraw: i2" + l);
    }
}

    x+=100;
}
flag2=0;
flag=0;
flag3=0;




        canvas.drawBitmap(mDrawBitmap, 0, 0, black);//drawing everything


    }//od


}//cv
