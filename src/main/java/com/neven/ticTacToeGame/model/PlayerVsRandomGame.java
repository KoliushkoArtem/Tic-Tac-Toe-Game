package com.neven.ticTacToeGame.model;

import java.util.Random;

public class PlayerVsRandomGame extends Game {

    private static final String PLAYER_2_NAME = "random";

    public PlayerVsRandomGame() {
        super(PLAYER_2_NAME);
    }

    @Override
    public Game winningCheckAndMakingMove(int cellNumber) {
        if (cells.get(cellNumber).equals(getEmptyCell())) {
            cells.put(cellNumber, getPlayer1());
        } else {
            setIncorrectMove(true);
            return this;
        }

        if (isPlayerWin(getPlayer1())) {
            setPlayer1win(true);
            return this;
        }

        if (isDraw()) {
            setDraw(true);
            return this;
        }

        makeRandomMove();

        if (isPlayerWin(getPlayer2())) {
            setPlayer2win(true);
            return this;
        }

        if (isDraw()) {
            setDraw(true);
            return this;
        }

        return this;
    }

    @Override
    public Game makeFirstMove() {
        makeRandomMove();
        return this;
    }

    private void makeRandomMove() {
        if (getCells().containsValue(getEmptyCell())) {
            int randomMove = new Random().nextInt(9) + 1;
            if (getCells().get(randomMove).equals(getEmptyCell())) {
                getCells().put(randomMove, getPlayer2());
            } else {
                makeRandomMove();
            }
        }
    }
}
