package com.highlow.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.highlow.assets.CardAssets;
import com.highlow.models.CardValue;
import com.highlow.models.Position;
import com.highlow.utils.Renderable;

public class Card implements Renderable {

    private CardValue cardValue;

    private final Position position;

    public Card(final CardValue cardValue, final Position position) {
        this.cardValue = cardValue;
        this.position = position;
    }

    public void render(final SpriteBatch batch) {
        if (cardValue == null || position == null) {
            return;
        }

        batch.draw(
                CardAssets.getCardTextureRegion(cardValue),
                position.getX(),
                position.getY()
        );
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public void setCardValue(CardValue cardValue) {
        this.cardValue = cardValue;
    }

    public Position getPosition() {
        return position;
    }
}