package com.jumper.game;


import com.badlogic.gdx.graphics.Texture;

public class InfoButton extends Button{

    Texture btn, btn_pressed;
    public InfoButton() {
        btn = new Texture("info.png");
        btn_pressed = new Texture("info_pressed.png");
    }

    Texture getButton() {
        if(ifPressed) return btn_pressed;
        else
            return btn;
    }
}
