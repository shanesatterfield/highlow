package com.highlow.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BaseScreen implements Screen {
    protected SpriteBatch batch;

    protected Stage stage;

    /**
     * A reference to the screen stack so that subclasses can update the active screens with their logic
     */
    protected ScreenStack screenStack;

    public BaseScreen(final ScreenStack screenStack) {
        this.screenStack = screenStack;
    }

    @Override
    public void show() {
        this.dispose();

        this.batch = new SpriteBatch();

        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

    @Override
    public void render(float delta) {
        this.batch.begin();

        // Handle UI events in the included stage
        stage.act(Gdx.graphics.getDeltaTime());

        // Draw UI elements to the screen that are attached to the stage
        stage.draw();

        this.renderInternal(delta);

        this.batch.end();
    }

    public abstract void renderInternal(float delta);

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        if (this.batch != null) {
            this.batch.dispose();
        }

        if (this.stage != null) {
            this.stage.dispose();
        }
    }
}
