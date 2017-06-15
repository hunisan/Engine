package com.example.csahunor.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;


/**
 * Created by pyr3 on 15.06.2017.
 */

public class TextObject extends GameObject {

    String text;
    int pt;
    public TextObject() {
    }

    public TextObject(String text, int x, int y, int pt)
    {
        this.text = text;
        this.x = x;
        this.y = y;
        this.pt = pt;
        this.h = pt;
        this.w = text.length()*pt;

    }
    @Override
    void draw(Canvas canvas, Paint p)
    {
        p.setTextSize(pt);
        p.setColor(Color.BLACK);
        canvas.drawText(text,x,y+pt,p);
    }

    @Override
    void onTap(int tapX, int tapY, GameState state)
    {
        state.add(new GameObject("mega",0,0,50,50));
    }
}
