package com.jumper.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.io.IOException;

public class State {

    GameStateManager gsm;
    OrthographicCamera camera;
    public State(GameStateManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Main.WIDTH / 2, Main.HEIGHT / 2);

    }

    void input() {
    }

    void rander(SpriteBatch sb) {

    }

    void update(float dt) {

    }

    void dispose() {

    }
}
