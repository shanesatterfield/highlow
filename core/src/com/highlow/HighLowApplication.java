package com.highlow;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.highlow.assets.CardAssets;
import com.highlow.assets.FontAssets;
import com.highlow.screens.BaseScreen;
import com.highlow.screens.MainMenuScreen;
import com.highlow.screens.ScreenStack;
import com.highlow.screens.game.GameScreen;

public class HighLowApplication extends ApplicationAdapter {

    private final ScreenStack screenStack = new ScreenStack();

    @Override
    public void create() {
        CardAssets.loadAssets();
        FontAssets.loadAssets();

        final BaseScreen startingScreen = new MainMenuScreen(this.screenStack);
        screenStack.pushScreen(startingScreen);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0.5f, 0.5f, 1);

        for (int i = 0; i < this.screenStack.activeScreenStack.size(); i++) {
            final Screen screen = this.screenStack.getActiveScreenStack().elementAt(i);
            screen.render(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void dispose() {
        this.screenStack.dispose();
    }
}
