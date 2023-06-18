package com.highlow.models;

public class CardValue {
    private final CardSuit suit;

    private final int rank;

    public CardValue(final CardSuit suit, final int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }
}
