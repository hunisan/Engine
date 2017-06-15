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
import java.util.ArrayList;
import java.util.List;

public class GameState {

    List<GameObject> objects = new ArrayList<GameObject>();

    void draw(Canvas c, Paint p)
    {

        for(GameObject o : objects){
            o.draw(c,p);
        }

    }
    void update()
    {
        for(GameObject o : objects){
            o.update(this);
        }
    }
    void tap(MotionEvent e)
    {
        int tapX = (int)e.getX();
        int tapY = (int)e.getY();

        for(GameObject o : objects)
            if(o.contains(tapX,tapY))
                o.onTap(tapX,tapY,this);

    }
    void add(GameObject newObject)
    {
        objects.add(newObject);
    }
}
