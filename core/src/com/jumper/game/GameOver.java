package com.jumper.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver extends State{

    Texture bg, btn;
    boolean HasPressed;
    BitmapFont font;
    int count;
    boolean Music;

    public GameOver(GameStateManager gsm, int score, boolean ifMusic) {
        super(gsm);
        bg = new Texture("bg.png");
        btn = new Texture("go.png");
        HasPressed = false;
        count = score;
        font = new BitmapFont();
        Music = ifMusic;
    }

    public void input() {
        if(Gdx.input.isTouched()) {
            HasPressed = true;
        }
        else {
            if(HasPressed) gsm.set(new PlayState(gsm, Music));
        }
    }

    public void update(float dt) {
        input();
    }

    public void rander(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, 0, 0, Main.WIDTH, Main.HEIGHT);
        sb.draw(btn, Main.WIDTH / 4 - btn.getWidth() / 4, Main.HEIGHT / 4 - btn.getHeight() / 4, btn.getWidth() / 2, btn.getHeight() / 2);
        font.draw(sb, "Your score: " + count, 85, 100);
        sb.end();
    }

    public void dispose() {
        bg.dispose();
        btn.dispose();
        font.dispose();
    }
}
