package com.example.blackjack;

public class BlackjackGame {
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private GameState state;

    public enum GameState {
        PLAYER_TURN, DEALER_TURN, PLAYER_WIN, DEALER_WIN, TIE
    }

    public BlackjackGame() {
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
        startNewGame();
    }

    public void startNewGame() {
        playerHand.clear();
        dealerHand.clear();

        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        state = GameState.PLAYER_TURN;
    }

    public void playerHit() {
        if (state == GameState.PLAYER_TURN) {
            playerHand.addCard(deck.drawCard());
            if (playerHand.isBusted()) {
                state = GameState.DEALER_WIN;
            }
        }
    }

    public void playerStand() {
        if (state == GameState.PLAYER_TURN) {
            state = GameState.DEALER_TURN;
            dealerPlay();
        }
    }

    private void dealerPlay() {
        while (dealerHand.calculateScore() < 17) {
            dealerHand.addCard(deck.drawCard());
        }

        if (dealerHand.isBusted() ||
                playerHand.calculateScore() > dealerHand.calculateScore()) {
            state = GameState.PLAYER_WIN;
        } else if (playerHand.calculateScore() < dealerHand.calculateScore()) {
            state = GameState.DEALER_WIN;
        } else {
            state = GameState.TIE;
        }
    }

    public Hand getPlayerHand() { return playerHand; }
    public Hand getDealerHand() { return dealerHand; }
    public GameState getGameState() { return state; }
}