package com.example.csahunor.engine;

/**
 * Created by pyr3 on 13.06.2017.
 */
import android.graphics.drawable.Drawable;
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
import java.util.*;

public class Cat {
    public static Map<String, Bitmap> images = new HashMap<>();


    public static void load(Context context)
    {
        for(String s : Owl.image_list)
        {
            int id =context.getResources().getIdentifier(s,"drawable",context.getPackageName());

            images.put(s,BitmapFactory.decodeResource(context.getResources(),id));
        }
    }

}
