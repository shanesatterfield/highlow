package com.highlow.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.highlow.screens.game.GameScreen;

/**
 * The main menu screen that displays the name of the game and allows the player to enter the game screen.
 */
public class MainMenuScreen extends TextScreen {

    public MainMenuScreen(final ScreenStack screenStack) {
        super(
                screenStack,
                "High Low",
                "Click or touch to start",
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        // Switch to the game screen when a player clicks or touches the screen
                        screenStack.switchScreen(new GameScreen(screenStack));
                        return true;
                    }
                });
    }
}
