package com.neven.ticTacToeGame.model;

public class PlayerVsAlgorithmGame extends Game {

    private static final String PLAYER_2_NAME = "algorithm";

    public PlayerVsAlgorithmGame() {
        super(PLAYER_2_NAME);
    }

    @Override
    public Game winningCheckAndMakingMove(int cellNumber) {
        return null;
    }

    @Override
    public Game makeFirstMove() {
        return null;
    }
}
