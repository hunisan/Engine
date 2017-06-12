package com.example.csahunor.engine;

/**
 * Created by pyr3 on 13.06.2017.
 */

public class GameState {

    GameObject[] objects;
    void draw()
    {
        for(GameObject o : objects){
            o.draw();
        }

    }
    void update()
    {
        for(GameObject o : objects){
            o.update();
        }
    }
    void onTap()
    {

    }
}
