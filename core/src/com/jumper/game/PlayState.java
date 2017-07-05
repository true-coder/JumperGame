package com.jumper.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.Queue;
import java.util.Random;




public class PlayState extends State {

    Music music;
    Sound pop;
    Texture bg;
    Queue<Tube> tubes;
    Player player;
    boolean ifHasInput;
    Random rand;
    BitmapFont font;
    boolean playMusic;
    double last_x;
    int count;

    float upSpeed;
    float downSpeed;
    float TubeSpeed;

    void comeBack() {
        upSpeed = 6; //6
        downSpeed = 1; //1
        TubeSpeed = 4; //4
    }
    double k_up = 0.9, k_tube = 1.005, g = 1.1;

    void GoToGameOver() {
        if(playMusic) music.stop();
        gsm.set(new GameOver(gsm, count, playMusic));
    }

    public PlayState(GameStateManager gsm, boolean ifMusic) {
        super(gsm);
        playMusic = ifMusic;
        font = new BitmapFont();
        bg = new Texture("bg.png");
        tubes = new Queue<Tube>();

        tubes.addFirst(new Tube(100));
        tubes.addLast(new Tube(tubes.get(0).getPosition().x + 200));
        tubes.addLast(new Tube(tubes.get(1).getPosition().x + 200));

        count = 0;
        player = new Player(100, tubes.get(0).getPosition().y + 320);
        ifHasInput = false;
        rand = new Random();

        comeBack();

        music = Gdx.audio.newMusic(Gdx.files.internal("MainMusic.mp3"));
        if(playMusic) {
            music.setLooping(true);
            music.play();
        }
    }

    public void update(float dt) {

        player.playerAnimation.update(dt);
        //Проверка на уход за грницу
        if (tubes.get(0).getPosition().x < -52) {
            tubes.removeFirst();
            tubes.addLast(new Tube(tubes.get(1).getPosition().x + 180 + rand.nextInt(40)) );
        }
        //Проверка на нажатие
        if (Gdx.input.isTouched() && upSpeed > 0.1) {
            player.reposition(player.getPosition().x, player.getPosition().y + upSpeed);
            upSpeed *= k_up; //Уменьшение скорости полета вверх

            last_x += TubeSpeed;

            for (Tube tube : tubes) tube.reposition(tube.getPosition().x - TubeSpeed); //Движение труб
        }
        else {

            boolean bool = false;

            for (Tube tube : tubes) {
                if (tube.getTb().overlaps(player.getPl())) {
                    bool = true;
                    comeBack(); //востановление значений
                    last_x = 0;
                    break;
                }
            }
            if (!bool) {
                player.reposition(player.getPosition().x, player.getPosition().y - downSpeed);
                for (Tube tube : tubes) tube.reposition(tube.getPosition().x - TubeSpeed);
                last_x += TubeSpeed;
                upSpeed = 0; //нельзя совершить прыжек, если ты не был на платформе после прошлого прыжка
                TubeSpeed*=k_tube; //уменьшение скорости при полете вниз (падении)
                downSpeed*=g; //ускорение падения
            }

            for (Tube tube : tubes) {
                if (tube.getTb().overlaps(player.getPl())) {
                    if(tube.getPosition().x > player.getPosition().x + player.getPl().getWidth() / 2 || tube.getPosition().x + tube.getTb().getWidth() < player.getPosition().x + player.getPl().getWidth() / 2 ) GoToGameOver();
                    player.reposition(player.getPosition().x, tube.getPosition().y + tube.getTb().getHeight() - 1);
                    if(last_x > 100) count++;
                    comeBack(); //востановление значений
                    last_x = 0;
                    break;
            }
        }
        if(player.getPosition().y < 100) GoToGameOver();
    }
}

    void rander(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, 0, 0, Main.WIDTH, Main.HEIGHT);
        sb.draw(player.getPlayer(), player.getPosition().x, player.getPosition().y, player.getPl().getWidth(), player.getPl().getHeight());
        for (Tube tube : tubes) {
            sb.draw(tube.getTube(), tube.getPosition().x, tube.getPosition().y);
        }

        font.draw(sb, "Score: " + count, 10, Main.HEIGHT / 2);
        sb.end();
    }

    void dispose() {
        music.dispose();
        bg.dispose();
    }
}
