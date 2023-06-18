package com.highlow.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.highlow.screens.game.GameScreen;

/**
 * The game over screen that shows the player's score and allows them to start a new game instance.
 */
public class GameOverScreen extends TextScreen {

    public GameOverScreen(final ScreenStack screenStack, final int score) {
        super(
                screenStack,
                "Game Over: " + score + " points",
                "Click or touch to start",
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        // Remove this screen from the stack
                        screenStack.removeScreen();

                        // Remove the previous game screen from the stack, with null-safety
                        screenStack.removeScreen();

                        // Add a new game screen to the stack
                        screenStack.pushScreen(new GameScreen(screenStack));
                        return true;
                    }
                });
    }
}
