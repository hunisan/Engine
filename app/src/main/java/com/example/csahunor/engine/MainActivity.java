package com.example.csahunor.engine;

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

public class MainActivity extends AppCompatActivity {

    GameView gameView;

    GameState currentState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        setContentView(gameView);

    }

    // GameView class w ill go here

    class GameView extends SurfaceView implements Runnable {
        Thread gameThread = null;
        SurfaceHolder ourHolder;
        volatile boolean playing;
        Canvas canvas;
        Paint paint;
        long fps;
        private long timeThisFrame;

        public GameView(Context context) {
            super(context);
            ourHolder = getHolder();
            paint = new Paint();

            currentState = new Menu();
        }

        @Override
        public void run() {
            while (playing) {
                long startFrameTime = System.currentTimeMillis();

                update();

                draw();

                timeThisFrame = System.currentTimeMillis() - startFrameTime;
                if (timeThisFrame > 0) {
                    fps = 1000 / timeThisFrame;
                }
            }
        }

        public void update() {
            currentState.update();
        }

        public void draw() {
            currentState.draw();
        }
        public void pause()
        {
            playing = false;
            try {
                gameThread.join();
            } catch(InterruptedException e){
                Log.e("Error:","joining thread");

            }

        }
        public void resume()
        {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
        public boolean onTouchEvent(MotionEvent motionEvent)
        {
            return true;
        }

    }
    // More SimpleGameEngine methods will go here

    @Override
    protected void onResume() {
        super.onResume();

        gameView.resume();
    }

    @Override
    protected void onPause(){
        super.onPause();

        gameView.pause();
    }
}