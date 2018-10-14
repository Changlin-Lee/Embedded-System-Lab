package com.example.changlinlee.lab2_2_pedometer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

public class DisplayView extends SurfaceView {

    private float mX = 0.0f;
    private float mY = 0.0f;
    private float mZ = 0.0f;
    private float mTotal_False = 0.0f;

    private float mCenterX =0.0f;
    private float mCenterY = 0.0f;
    private float mRadius = 0.0f;

    private float mPtrCenterX = 160.0f;
    private float mPtrCenterY = 300.0f;
    private float mPtrRadius = 10.0f;

    private final static int TYPE_BALL = 0;
    private int mPtrType = TYPE_BALL;
    private int mPtrColor = Color.WHITE;

    public DisplayView(Context context, AttributeSet attrs){
        super(context, attrs);

        setWillNotDraw(false);
    }

    public void setPtr(float mX, float mY, float mZ )

    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.BLACK);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(140,200, 180,400, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(140,240, 180,280, paint);
        paint.setColor(Color.GREEN);
        canvas.drawRect(140, 280,180,320, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(140, 320,180,360, paint);
        paint.setColor(Color.RED);
        canvas.drawRect(140, 360,180,400, paint);

        paint.setColor(mPtrColor);
        canvas.drawCircle(mPtrCenterX, mPtrCenterY, mPtrRadius, paint);
    }


    /*public void onSizeChanged(int width, int height, int oldWidth, int oldHeight){

    }*/
}
