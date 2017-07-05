package com.jumper.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MainMenu extends State{

    Texture poweredBy;
    Texture bg;
    SoundButton sbtn;
    Rectangle startBtn, soundBtn, Input, info;
    BitmapFont font;
    Vector3 v;
    boolean ifPressed;
    PlayButton btn;
    InfoButton ibtn;

    public MainMenu(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("bg.png");
        font = new BitmapFont();

        Gdx.gl.glClearColor(0, 0, 0, 0);

        ifPressed = false;
        poweredBy = new Texture("by.png");
        btn = new PlayButton();
        sbtn = new SoundButton();
        ibtn = new InfoButton();

        startBtn = new Rectangle(Main.WIDTH / 4 - btn.getWidth() / 2, Main.HEIGHT / 4 - btn.getHeight() / 2, btn.getWidth(), btn.getHeight());
        soundBtn = new Rectangle(10, Main.HEIGHT / 2 - sbtn.getButton().getHeight() / 2 - 10, sbtn.getButton().getWidth() / 2, sbtn.getButton().getHeight() / 2);
        info = new Rectangle(10, Main.HEIGHT / 2 - ibtn.getButton().getHeight() - 10 - 10, ibtn.getButton().getWidth() / 2, ibtn.getButton().getHeight() / 2);
        Input = new Rectangle(0, 0, 1, 1);
        v = new Vector3(0, 0, 0);
    }

    public void input() {
        if(Gdx.input.isTouched()) {
            v.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(v);
            Input.setPosition(v.x, v.y);
            ifPressed = true;

            if(Input.overlaps(soundBtn)) sbtn.pressed(); // выдаем нажатую текстуру
            else
                sbtn.not_pressed(); // выдаем не нажатую текстуру

            if(Input.overlaps(startBtn)) btn.pressed();
            else
                btn.not_pressed();

            if(Input.overlaps(info)) ibtn.pressed();
            else
                ibtn.not_pressed();
        }
        else {
            if (Input.overlaps(startBtn) && ifPressed) gsm.set(new PlayState(gsm, !sbtn.getIfTochaed()));
            if (Input.overlaps(soundBtn)  && ifPressed) sbtn.Toched();
            if(Input.overlaps(info) && ifPressed) gsm.push(new InfoState(gsm));

            ifPressed = false;
            //т.к сейчас нет нажатия возвращаем нормальные текстуры
            sbtn.not_pressed();
            btn.not_pressed();
            ibtn.not_pressed();
        }
    }

    public void update(float dt) {
        input();
    }

    public void rander(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, 0, 0, Main.WIDTH, Main.HEIGHT);
        sb.draw(btn.getBtn(), Main.WIDTH / 4 - btn.getWidth() / 2, Main.HEIGHT / 4 - btn.getHeight() / 2);
        sb.draw(sbtn.getButton(), 10, Main.HEIGHT / 2 - sbtn.getButton().getHeight() / 2 - 10, sbtn.getButton().getWidth() /2, sbtn.getButton().getHeight() / 2);
        sb.draw(ibtn.getButton(), 10, Main.HEIGHT / 2 - ibtn.getButton().getHeight() - 10 - 10, ibtn.getButton().getWidth() / 2, ibtn.getButton().getHeight() / 2);
        sb.end();
    }

    public void dispose() {
        bg.dispose();
    }
}
