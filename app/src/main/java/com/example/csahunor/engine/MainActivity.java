package com.example.csahunor.engine;

import android.graphics.Point;
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
import android.view.GestureDetector.SimpleOnGestureListener;
import android.support.v4.view.GestureDetectorCompat;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    GameView gameView;

    GameState currentState;

    private GestureDetectorCompat mDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        setContentView(gameView);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {

            return true;
        }
        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            gameView.onTap(event);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            return true;
        }
    }
    // GameView class will go here

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

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            ourHolder = getHolder();
            paint = new Paint();

            Cat.load(context);

            //Get screen dimensions
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            Owl.screen_width=size.x;
            Owl.screen_height=size.y;

            currentState = new Menu();
            currentState.objects.add(new TextObject(Owl.title,0,0,50));
            //currentState.objects.add(new GameObject("mega",0,0,Owl.screen_width,Owl.screen_width));
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

            if (ourHolder.getSurface().isValid()) {
                canvas = ourHolder.lockCanvas();

                canvas.drawColor(Color.argb(255,  26, 128, 182));

                currentState.draw(canvas,paint);

                ourHolder.unlockCanvasAndPost(canvas);
            }
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

        public void onTap(MotionEvent event)
        {
            currentState.tap(event);
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