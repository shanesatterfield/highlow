package com.highlow.screens.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HighLowButtonListener extends ClickListener {

    private final HighLowStateSetter highLowStateSetter;

    private final boolean value;

    public HighLowButtonListener(final HighLowStateSetter setter, final boolean value) {
        this.highLowStateSetter = setter;
        this.value = value;
    }

    @Override
    public void clicked(final InputEvent event, float x, float y) {
        this.highLowStateSetter.setHighLowGuess(this.value);
    }
}
