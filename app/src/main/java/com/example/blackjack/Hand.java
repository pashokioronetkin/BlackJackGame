package com.example.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = 0;
        int aces = 0;

        for (Card card : cards) {
            score += card.getValue();
            if (card.getRank().equals("A")) aces++;
        }

        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }

        return score;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public void clear() {
        cards.clear();
    }

    public boolean isBusted() {
        return calculateScore() > 21;
    }
}