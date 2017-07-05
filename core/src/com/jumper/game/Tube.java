package com.jumper.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;


public class Tube
{
    float x;
    Texture tube;
    private Vector2 position;
    Rectangle tb;
    private Random rand;

    public Tube(float x_pos) {
        x = x_pos;
        tube = new Texture("tube2.png");
        rand = new Random();
        position = new Vector2(x, rand.nextInt(50) - 200);
        tb = new Rectangle(position.x, position.y, tube.getWidth(), tube.getHeight());
    }

    public void reposition(float x_pos) {
        x = x_pos;
        position.set(x, position.y);
        tb.setPosition(x, position.y);
    }
    public Vector2 getPosition(){
        return position;
    }

    public Rectangle getTb() {
        return tb;
    }

    public Texture getTube(){
        return tube;
    }
    public void dispose() {
        tube.dispose();
    }
}
