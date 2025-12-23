package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BlackjackGame game;
    private TextView tvDealerCards, tvPlayerCards, tvScore, tvResult;
    private Button btnHit, btnStand, btnNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Находим все элементы
        tvDealerCards = findViewById(R.id.tvDealerCards);
        tvPlayerCards = findViewById(R.id.tvPlayerCards);
        tvScore = findViewById(R.id.tvScore);
        tvResult = findViewById(R.id.tvResult);
        btnHit = findViewById(R.id.btnHit);
        btnStand = findViewById(R.id.btnStand);
        btnNewGame = findViewById(R.id.btnNewGame);

        // Создаем игру
        game = new BlackjackGame();

        // Назначаем обработчики кнопок
        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerHit();
            }
        });

        btnStand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerStand();
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });

        // Показываем начальное состояние
        updateUI();
    }

    private void playerHit() {
        game.playerHit();
        updateUI();

        if (game.getGameState() == BlackjackGame.GameState.DEALER_WIN) {
            tvResult.setText("Перебор! Вы проиграли.");
        }
    }

    private void playerStand() {
        game.playerStand();
        updateUI();

        switch (game.getGameState()) {
            case PLAYER_WIN:
                tvResult.setText("Поздравляем! Вы выиграли!");
                break;
            case DEALER_WIN:
                tvResult.setText("Дилер выиграл.");
                break;
            case TIE:
                tvResult.setText("Ничья!");
                break;
        }
    }

    private void newGame() {
        game.startNewGame();
        tvResult.setText("");
        updateUI();
    }

    private void updateUI() {
        // Карты дилера (первая скрыта во время хода игрока)
        StringBuilder dealerCards = new StringBuilder("Карты: ");
        if (game.getGameState() == BlackjackGame.GameState.PLAYER_TURN) {
            var cards = game.getDealerHand().getCards();
            if (!cards.isEmpty()) {
                dealerCards.append(cards.get(0)).append(" ??");
            }
        } else {
            for (Card card : game.getDealerHand().getCards()) {
                dealerCards.append(card).append(" ");
            }
            dealerCards.append("(").append(game.getDealerHand().calculateScore()).append(")");
        }
        tvDealerCards.setText(dealerCards.toString());

        // Карты игрока
        StringBuilder playerCards = new StringBuilder("Карты: ");
        for (Card card : game.getPlayerHand().getCards()) {
            playerCards.append(card).append(" ");
        }
        playerCards.append("(").append(game.getPlayerHand().calculateScore()).append(")");
        tvPlayerCards.setText(playerCards.toString());

        // Счет
        tvScore.setText("Счет: " + game.getPlayerHand().calculateScore());

        // Блокируем кнопки если игра окончена
        boolean gameActive = game.getGameState() == BlackjackGame.GameState.PLAYER_TURN;
        btnHit.setEnabled(gameActive);
        btnStand.setEnabled(gameActive);
    }
}