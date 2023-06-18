package com.highlow.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;

import java.util.Stack;

/**
 * A stack of screens that can be used to manage which game screens are currently active and displayed.
 */
public class ScreenStack implements Disposable {

    public Stack<Screen> activeScreenStack = new Stack<>();

    /**
     * Add a screen onto the stack.
     *
     * @param screen Screen to add to the stack
     */
    public void pushScreen(final Screen screen) {
        this.activeScreenStack.push(screen);
        this.getActiveScreen().show();
    }

    /**
     * Replace the current screen with the new screen.
     * If there is a currently active screen, it will be removed before adding the new screen.
     *
     * @param screen The screen to switch into
     */
    public void switchScreen(final Screen screen) {
        if (!this.activeScreenStack.isEmpty()) {
            this.removeScreen();
        }
        this.pushScreen(screen);
    }

    /**
     * Pops a screen off the stack.
     *
     * @return The previously active screen on the stack
     */
    public Screen popScreen() {
        return this.activeScreenStack.pop();
    }

    /**
     * Removes and disposes the currently active screen
     */
    public void removeScreen() {
        if (!this.activeScreenStack.isEmpty()) {
            this.popScreen().dispose();
        }
    }

    /**
     * Get the currently active screen without removing it from the stack.
     *
     * @return The currently active screen
     */
    public Screen getActiveScreen() {
        return this.activeScreenStack.peek();
    }

    /**
     * Get the entire, internal screen stack
     *
     * @return The internal screen stack
     */
    public Stack<Screen> getActiveScreenStack() {
        return activeScreenStack;
    }

    /**
     * Dispose of the screen stack resources, which will remove and dispose of all stored screens.
     */
    @Override
    public void dispose() {
        while (!this.activeScreenStack.isEmpty()) {
            this.activeScreenStack.pop().dispose();
        }
    }
}
