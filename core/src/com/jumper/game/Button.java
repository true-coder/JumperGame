package com.jumper.game;



public class Button {

    boolean ifPressed;
    public Button() {
        ifPressed = false;
    }

    void pressed() { ifPressed = true; }

    void not_pressed() { ifPressed = false; }

}
