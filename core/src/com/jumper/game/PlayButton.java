package com.jumper.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Oleg on 31.05.2017.
 */

public class PlayButton extends Button{

    Texture btn, btn_pressed;

    public PlayButton() {
        btn = new Texture("playbtn_1.png");
        btn_pressed = new Texture("playbtn.png");
    }

    Texture getBtn() {
        if(ifPressed) return btn_pressed;
            else return btn;
    }

    float getWidth() {
        return btn.getWidth();
    }

    float getHeight() {
        return btn.getHeight();
    }
}
