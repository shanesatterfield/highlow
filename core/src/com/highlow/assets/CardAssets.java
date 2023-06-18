package com.highlow.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.highlow.Config;
import com.highlow.models.CardSuit;
import com.highlow.models.CardValue;

import java.util.HashMap;
import java.util.Map;

public class CardAssets {

    private static Texture cardDeck;

    private static Sprite cardDeckSprite;

    private static Map<CardSuit, Texture> cardTextureBySuit;

    private static final String cardAssetsBasePath = "card-assets/Top-Down/Cards/";

    public static void loadAssets() {
        dispose();

        // Setup card deck
        cardDeck = new Texture(cardAssetsBasePath + "Card Deck - Top Down 88x140.png");
        final int cardDeckWidth = cardDeck.getWidth() / 3;
        cardDeckSprite = new Sprite(cardDeck, 0, 0, cardDeckWidth, cardDeck.getHeight());

        // Set up card textures by suit
        cardTextureBySuit = new HashMap<>();
        cardTextureBySuit.put(CardSuit.Clubs, new Texture(getCardTextureBySuit("Clubs")));
        cardTextureBySuit.put(CardSuit.Diamonds, new Texture(getCardTextureBySuit("Diamonds")));
        cardTextureBySuit.put(CardSuit.Hearts, new Texture(getCardTextureBySuit("Hearts")));
        cardTextureBySuit.put(CardSuit.Spades, new Texture(getCardTextureBySuit("Spades")));
    }

    public static void dispose() {
        if (cardDeck != null) {
            cardDeck.dispose();
        }

        if (cardTextureBySuit != null) {
            for (final Texture texture : cardTextureBySuit.values()) {
                texture.dispose();
            }
            cardTextureBySuit.clear();
        }
    }

    public static Sprite getCardDeckSprite() {
        return cardDeckSprite;
    }

    public static Texture getCardTexture(final CardSuit suit) {
        if (cardTextureBySuit == null) {
            return null;
        }
        return cardTextureBySuit.get(suit);
    }

    public static TextureRegion getCardTextureRegion(final CardValue cardValue) {
        final int x = Config.CARD_WIDTH * ((cardValue.getRank() - 1) % 5);
        final int y = Config.CARD_HEIGHT * ((cardValue.getRank() - 1) / 5);
        return new TextureRegion(getCardTexture(cardValue.getSuit()), x, y, Config.CARD_WIDTH, Config.CARD_HEIGHT);
    }

    private static String getCardTextureBySuit(final String suit) {
        return cardAssetsBasePath + suit + " - Top Down 88x124.png";
    }
}
