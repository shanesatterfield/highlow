package com.highlow.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontAssets {

    private static BitmapFont titleFont;

    private static BitmapFont subtitleFont;

    private static BitmapFont textLabelFont;

    public static void loadAssets() {
        final String fontPath = "fonts/rainyhearts.ttf";
        final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        final FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 64;
        parameter.color = Color.BLACK;
        titleFont = generator.generateFont(parameter);

        parameter.size = 48;
        parameter.color = Color.BLACK;
        subtitleFont = generator.generateFont(parameter);

        parameter.size = 32;
        parameter.color = Color.BLACK;
        textLabelFont = generator.generateFont(parameter);

        generator.dispose();
    }

    public static void dispose() {

    }

    public static BitmapFont getTitleFont() {
        return titleFont;
    }

    public static BitmapFont getSubtitleFont() {
        return subtitleFont;
    }

    public static BitmapFont getTextLabelFont() {
        return textLabelFont;
    }
}
