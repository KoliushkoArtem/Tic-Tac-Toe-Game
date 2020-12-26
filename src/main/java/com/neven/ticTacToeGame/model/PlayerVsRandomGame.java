package com.neven.ticTacToeGame.model;

import java.util.Random;

public class PlayerVsRandomGame extends Game {

    private static final String PLAYER_2_NAME = "random";

    public PlayerVsRandomGame() {
        super(PLAYER_2_NAME);
    }

    @Override
    public Game makeFirstMove() {
        makeMove();
        return this;
    }

    @Override
    protected void makeMove() {
        if (getCells().containsValue(getEmptyCell())) {
            int randomMove = new Random().nextInt(9) + 1;
            if (getCells().get(randomMove).equals(getEmptyCell())) {
                getCells().put(randomMove, getPlayer2());
            } else {
                makeMove();
            }
        }
    }
}
