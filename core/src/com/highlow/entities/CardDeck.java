package com.highlow.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.highlow.assets.CardAssets;
import com.highlow.Config;
import com.highlow.models.Position;
import com.highlow.utils.Renderable;

public class CardDeck implements Renderable {

    private final Position position;

    private final Sprite sprite;

    public CardDeck() {
        this.sprite = CardAssets.getCardDeckSprite();

        this.position = new Position(
                (Gdx.graphics.getWidth() / 2.0f) + (Config.CARD_WIDTH * 1f),
                (Gdx.graphics.getHeight() / 2.0f) - (Config.CARD_HEIGHT / 2.0f)
        );
    }

    @Override
    public void render(final SpriteBatch batch) {
        this.sprite.setPosition(this.position.getX(), this.position.getY());
        this.sprite.draw(batch);
    }
}
