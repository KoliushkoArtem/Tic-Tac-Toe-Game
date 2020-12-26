package com.neven.ticTacToeGame.model;

public class PlayerVsPlayerGame extends Game {

    private static final String PLAYER_2_NAME = "user_2";

    public PlayerVsPlayerGame() {
        super(PLAYER_2_NAME);
    }

    @Override
    public Game makeFirstMove() {
        switchCurrentPlayer();
        return this;
    }

    @Override
    protected void makeMove() {
        switchCurrentPlayer();
    }
}