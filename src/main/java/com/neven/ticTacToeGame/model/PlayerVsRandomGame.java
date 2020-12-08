package com.neven.ticTacToeGame.model;

public class PlayerVsRandomGame extends Game {

    private static final String PLAYER_2_NAME = "random";

    public PlayerVsRandomGame() {
        super(PLAYER_2_NAME);
    }

    @Override
    public Game winningCheckAndMakingMove(int cellNumber) {
        return null;
    }
}
