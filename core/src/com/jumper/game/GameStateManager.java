package com.jumper.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jumper.game.State;

import java.util.Stack;

public class GameStateManager {

    Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();

    }

    public void pop() {
        states.pop().dispose();
    }

    public void push(State state) {
        states.push(state);
    }

    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void rander(SpriteBatch sb) {
        states.peek().rander(sb);
    }

    public void dispose() {

    }
}
