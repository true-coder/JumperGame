package com.jumper.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.omg.CORBA.PRIVATE_MEMBER;

public class  Player {
    Texture  texture;
    Vector2 position;
    Rectangle pl;
    public Animation playerAnimation;

    public Player(float x, float y) {
        texture = new Texture("birdanimation3.png");
        playerAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        position = new Vector2(x, y);
        pl = new Rectangle(position.x, position.y, texture.getWidth() / 3, texture.getHeight());
    }


    public void reposition(float x, float y) {
        position.set(x, y);
        pl.setPosition(x, y);
    }

    public Rectangle getPl() {
        return pl;
    }

    public TextureRegion getPlayer() {
        return playerAnimation.getFrame();
    }

    public Vector2 getPosition() {
        return position;
    }
}
