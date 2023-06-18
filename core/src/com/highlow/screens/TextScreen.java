package com.highlow.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.highlow.assets.FontAssets;
import com.highlow.screens.game.GameScreen;

public class TextScreen extends BaseScreen {

    protected String title;

    protected String subtitle;

    protected InputListener inputListener;

    protected Texture textBackgroundTexture;

    protected final float targetWidth = 600;

    protected final float targetHeight = 400;

    /**
     * Construct a screen of text with an attached event listener
     *
     * @param screenStack A reference to the ScreenStack that this screen will be attached to
     * @param title The title text to display
     * @param subtitle The subtitle text to display in a smaller font, below the title
     * @param inputListener An input event lister that will be attached to the screen with custom logic
     */
    public TextScreen(final ScreenStack screenStack, final String title, final String subtitle, final InputListener inputListener) {
        super(screenStack);
        this.title = title;
        this.subtitle = subtitle;
        this.inputListener = inputListener;
        this.textBackgroundTexture = this.createTextBackgroundRect();
    }

    @Override
    public void show() {
        super.show();
        this.stage.addListener(inputListener);
    }

    @Override
    public void renderInternal(float delta) {
        final float titleX = (Gdx.graphics.getWidth() / 2.f) - (targetWidth / 2.f);
        final float titleY = (Gdx.graphics.getHeight() / 2.f) + (FontAssets.getTitleFont().getCapHeight() * 3.f);
        final float subtitleY = (Gdx.graphics.getHeight() / 2.f) - (FontAssets.getSubtitleFont().getCapHeight() * 3.f);

        // Draw transparent background to make the text easier to read
        batch.draw(
                this.textBackgroundTexture,
                (Gdx.graphics.getWidth() - targetWidth) / 2.f,
                (Gdx.graphics.getHeight() - targetHeight) / 2.f,
                targetWidth,
                targetHeight);

        // Draw the text onto the screen
        FontAssets.getTitleFont().draw(this.batch, this.title, titleX, titleY, targetWidth, 1, true);
        FontAssets.getSubtitleFont().draw(this.batch, this.subtitle, titleX, subtitleY, targetWidth, 1, true);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Create a quad texture for the background of the text with defaults.
     * This is done once, ahead of time, and stored to be reused later.
     *
     * @return A quad to render behind text to make it easier to read
     */
    protected Texture createTextBackgroundRect() {
        final float titleY = (Gdx.graphics.getHeight() / 2.f) + (FontAssets.getTitleFont().getCapHeight() * 2.f);
        final float subtitleY = (Gdx.graphics.getHeight() / 2.f) - (FontAssets.getSubtitleFont().getCapHeight() * 2.f);

        final Color color = new Color(1, 1, 1, 0.75f);
        return createTexture((int)targetWidth, (int)(titleY + subtitleY), color);
    }

    /**
     * Create texture of a quad with the given dimensions and color.
     *
     * @param width Width of the quad
     * @param height Height of the quad
     * @param color Color of the quad
     * @return A Texture object representing the quad
     */
    protected Texture createTexture(final int width, final int height, final Color color) {
        final Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);

        final Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return texture;
    }
}
