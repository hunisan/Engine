package com.example.csahunor.engine;

/**
 * Created by pyr3 on 13.06.2017.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameObject {
    String image;
    float x,y;
    int w,h;


    void draw(Canvas canvas, Paint p)
    {
        canvas.drawBitmap(Cat.images.get(image),(int)x,(int)y,p);
    }

    void update()
    {

    }
}
