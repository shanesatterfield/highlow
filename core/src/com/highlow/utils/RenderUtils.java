package com.highlow.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderUtils {

    /**
     * Convenience, null-safe method that renders a Renderable with a SpriteBatch
     * @param batch The SpriteBatch to render onto (null-safe)
     * @param renderable The renderable to render (null-safe)
     */
    public static void render(final SpriteBatch batch, final Renderable renderable) {
        // Check for nulls before rendering
        if (batch == null || renderable == null) {
            return;
        }
        renderable.render(batch);
    }
}
