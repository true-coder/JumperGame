package com.jumper.game;


import com.badlogic.gdx.graphics.Texture;

public class SoundButton extends  Button{

    Texture t1, t2, t1_pressed, t2_pressed;
    boolean ifTochaed;

    public boolean getIfTochaed() {
        return ifTochaed;
    }

    public SoundButton() {
        t1_pressed = new Texture("sound_allowed_pressed.png");
        t2_pressed = new Texture("sound_no_pressed.png");
        t1 = new Texture("sound_allowed.png");
        t2 = new Texture("sound_no.png");

        ifTochaed = false;
    }

    void Toched() {
        ifTochaed = !ifTochaed;
    }

    Texture getButton() {
        if(ifTochaed)
            if(ifPressed) { return t2_pressed; } else { return t2; }
        else
            if(ifPressed) { return t1_pressed; } else  { return t1; }
    }
}
