package com.jumper.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InfoState extends State{

    Texture by;
    float x, y;
    public InfoState(GameStateManager gsm) {
        super(gsm);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        by = new Texture("info_text.png");
        x = Main.WIDTH / 4 - by.getWidth() / 2;
        y = Main.HEIGHT / 2;
    }

    void input() {
        if(Gdx.input.isTouched()) gsm.pop();
    }
    void rander(SpriteBatch sb) {

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(by, x, y);
        sb.end();
    }

    void update(float dt) {
        input();
        y-=1;
        if(y <= 0) gsm.pop();
    }

    void dispose() {

    }
}
