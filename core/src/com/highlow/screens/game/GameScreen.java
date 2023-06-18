package com.highlow.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.highlow.Config;
import com.highlow.assets.FontAssets;
import com.highlow.entities.Card;
import com.highlow.entities.CardDeck;
import com.highlow.models.CardSuit;
import com.highlow.models.CardValue;
import com.highlow.models.Position;
import com.highlow.screens.BaseScreen;
import com.highlow.screens.GameOverScreen;
import com.highlow.screens.ScreenStack;
import com.highlow.utils.RenderUtils;

import java.util.Collections;
import java.util.LinkedList;

public class GameScreen extends BaseScreen implements HighLowStateSetter {

    private LinkedList<CardValue> cardDeckValues;

    private CardDeck cardDeck;

    private Card activeCard;

    private Card nextCard;

    private Button guessHigherButton;

    private Button guessLowerButton;

    private int score = 0;

    public GameScreen(final ScreenStack screenStack) {
        super(screenStack);
    }

    @Override
    public void show() {
        super.show();

        // Create and shuffle the deck
        this.shuffleDeck();

        if (this.cardDeck == null) {
            this.cardDeck = new CardDeck();
        }

        if (this.activeCard == null) {
            final CardValue nextCard = this.drawNextCard();
            this.activeCard = new Card(
                    nextCard,
                    new Position(
                            (Gdx.graphics.getWidth() / 2.0f) - (Config.CARD_WIDTH * 2f),
                            (Gdx.graphics.getHeight() / 2.0f) - (Config.CARD_HEIGHT / 2.0f))
            );
        }

        if (this.nextCard == null) {
            this.nextCard = new Card(
                    // Set to null because we haven't pulled the next card yet
                    null,
                    new Position(
                            (Gdx.graphics.getWidth() / 2.0f) - (Config.CARD_WIDTH * 1f),
                            (Gdx.graphics.getHeight() / 2.0f) - (Config.CARD_HEIGHT / 2.0f))
            );
        }

        if (this.guessHigherButton == null) {
            final Drawable drawable = new TextureRegionDrawable(new Texture("higher-button.png"));
            this.guessHigherButton = new ImageButton(drawable);
            this.guessHigherButton.setX((Gdx.graphics.getWidth() / 2.f) - (Config.BUTTON_DIM * 2));
            this.guessHigherButton.setY(Config.BUTTON_DIM);

            this.guessHigherButton.addListener(new HighLowButtonListener(this, true));
            this.stage.addActor(this.guessHigherButton);
        }

        if (this.guessLowerButton == null) {
            final Sprite sprite = new Sprite(new Texture("higher-button.png"));
            sprite.flip(false, true);

            this.guessLowerButton = new ImageButton(new TextureRegionDrawable(sprite));
            this.guessLowerButton.setX((Gdx.graphics.getWidth() / 2.f) + Config.BUTTON_DIM);
            this.guessLowerButton.setY(Config.BUTTON_DIM);

            this.guessLowerButton.addListener(new HighLowButtonListener(this, false));
            this.stage.addActor(this.guessLowerButton);
        }
    }

    @Override
    public void renderInternal(float delta) {
        // Render score counter
        final float targetWidth = 600;
        final float scoreTextX = (Gdx.graphics.getWidth() - targetWidth) / 2.f;
        final float scoreTextY = Gdx.graphics.getHeight() - 10;
        FontAssets.getTextLabelFont().draw(
                this.batch,
                "Score: " + score + " | Cards left: " + this.cardDeckValues.size(),
                scoreTextX,
                scoreTextY,
                targetWidth,
                1,
                true);

        RenderUtils.render(batch, this.cardDeck);
        RenderUtils.render(batch, this.activeCard);
        RenderUtils.render(batch, this.nextCard);
    }

    public void shuffleDeck() {
        final LinkedList<CardValue> cards = new LinkedList<>();
        for (final CardSuit suit : CardSuit.values()) {
            for (int i = 1; i <= 13; i++) {
                cards.add(new CardValue(suit, i));
            }
        }
        Collections.shuffle(cards);
        this.cardDeckValues = cards;
    }

    @Override
    public void setHighLowGuess(boolean guessHigher) {
        // Move the next card to the active card
        if (this.nextCard.getCardValue() != null) {
            this.activeCard.setCardValue(this.nextCard.getCardValue());
        }

        // Pull the next card
        this.nextCard.setCardValue(this.drawNextCard());

        // Check if the next card has a higher rank than the previous card
        final boolean isHigher = isHigherRank(
                this.nextCard.getCardValue(),
                this.activeCard.getCardValue());

        if (isHigher == guessHigher) {
            // Increase the score on a correct guess
            score++;
        } else {
            // Render the game over screen on top of this one, so you can still see the cards
            this.screenStack.pushScreen(new GameOverScreen(this.screenStack, score));
        }
    }

    public boolean isHigherRank(final CardValue a, final CardValue b) {
        final int rankA = a.getRank() == 1 ? 14 : a.getRank();
        final int rankB = b.getRank() == 1 ? 14 : b.getRank();
        return rankA > rankB;
    }

    public CardValue drawNextCard() {
        while (true) {
            if (this.cardDeckValues.isEmpty()) {
                Gdx.app.log("debug", "No more cards! Shuffling a new deck");
                this.shuffleDeck();
            }

            final CardValue next = this.cardDeckValues.pop();

            // Return immediately if the active card was not initialized
            if (this.activeCard == null) {
                return next;
            }

            // Only return the card if it is not the same rank as the currently active card
            if (next.getRank() != this.activeCard.getCardValue().getRank()) {
                return next;
            }
        }
    }


    @Override
    public void dispose() {
        super.dispose();
    }
}
