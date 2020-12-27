package com.neven.ticTacToeGame.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.TreeMap;

@Data
public abstract class Game {

    private final String player1 = "user_1";
    private final String player2;
    private final String emptyCell = "empty";
    @Setter(value = AccessLevel.PROTECTED)
    private String currentPlayer;
    @Setter(value = AccessLevel.PROTECTED)
    private boolean player1win = false;
    @Setter(value = AccessLevel.PROTECTED)
    private boolean player2win = false;
    @Setter(value = AccessLevel.PROTECTED)
    private boolean draw = false;
    @Setter(value = AccessLevel.PROTECTED)
    private boolean incorrectMove = false;
    @Setter(value = AccessLevel.PRIVATE)
    private Map<Integer, String> cells = new TreeMap<>();

    public Game(String player_2) {
        this.player2 = player_2;
        this.currentPlayer = player1;

        for (int i = 1; i <= 9; i++) {
            cells.put(i, emptyCell);
        }
    }

    public Game winningCheckAndMakingMove(int cellNumber) {
        if (cells.get(cellNumber).equals(getEmptyCell())) {
            if (currentPlayer.equals(player1)) {
                cells.put(cellNumber, getPlayer1());
            } else {
                cells.put(cellNumber, getPlayer2());
            }
        } else {
            setIncorrectMove(true);
            return this;
        }

        if (isPlayerWin(currentPlayer)) {
            setWinner();
            return this;
        }

        if (isItDraw()) {
            setDraw(true);
            return this;
        }

        if (this instanceof PlayerVsPlayerGame) {
            makeMove();
        } else {
            switchCurrentPlayer();
            makeMove();

            if (isPlayerWin(currentPlayer)) {
                setWinner();
                return this;
            }

            if (isItDraw()) {
                setDraw(true);
                return this;
            }

            switchCurrentPlayer();
        }

        return this;
    }

    protected void switchCurrentPlayer() {
        if (currentPlayer.equals(player1)) {
            setCurrentPlayer(player2);
        } else {
            setCurrentPlayer(player1);
        }
    }

    protected boolean isPlayerWin(@NotNull String player) {
        boolean line_1 = cells.get(1).equals(player) && cells.get(2).equals(player) && cells.get(3).equals(player);
        boolean line_2 = cells.get(4).equals(player) && cells.get(5).equals(player) && cells.get(6).equals(player);
        boolean line_3 = cells.get(7).equals(player) && cells.get(8).equals(player) && cells.get(9).equals(player);
        boolean line_4 = cells.get(1).equals(player) && cells.get(4).equals(player) && cells.get(7).equals(player);
        boolean line_5 = cells.get(2).equals(player) && cells.get(5).equals(player) && cells.get(8).equals(player);
        boolean line_6 = cells.get(3).equals(player) && cells.get(6).equals(player) && cells.get(9).equals(player);
        boolean line_7 = cells.get(1).equals(player) && cells.get(5).equals(player) && cells.get(9).equals(player);
        boolean line_8 = cells.get(3).equals(player) && cells.get(5).equals(player) && cells.get(7).equals(player);

        return line_1 || line_2 || line_3 || line_4 || line_5 || line_6 || line_7 || line_8;
    }

    private void setWinner() {
        if (currentPlayer.equals(player1)) {
            setPlayer1win(true);
        }

        if (currentPlayer.equals(player2)) {
            setPlayer2win(true);
        }
    }

    private boolean isItDraw() {
        return !isPlayerWin(player1) && !isPlayerWin(player2) && !cells.containsValue(emptyCell);
    }

    public abstract Game makeFirstMove();

    protected abstract void makeMove();
}