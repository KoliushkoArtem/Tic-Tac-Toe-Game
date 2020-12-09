package com.neven.ticTacToeGame.model;

import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

@Data
public abstract class Game {

    private final String player1 = "user_1";
    private final String player2;
    private final String emptyCell = "empty";
    private String currentPlayer;

    private boolean player1win = false;
    private boolean player2win = false;
    private boolean draw = false;
    private boolean incorrectMove = false;

    public Map<Integer, String> cells = new TreeMap<>();

    public Game(String player_2) {
        this.player2 = player_2;
        this.currentPlayer = player1;

        for (int i = 1; i <= 9; i++) {
            cells.put(i, emptyCell);
        }
    }

    protected void switchCurrentPlayer() {
        if (currentPlayer.equals(player1)) {
            setCurrentPlayer(player2);
        } else {
            setCurrentPlayer(player1);
        }
    }

    public boolean isPlayerWin(String player) {
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

    public boolean isDraw() {
        return !cells.containsValue(emptyCell);
    }

    public abstract Game winningCheckAndMakingMove(int cellNumber);
    public abstract Game makeFirstMove();
}