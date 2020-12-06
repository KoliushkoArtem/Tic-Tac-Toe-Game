package com.neven.ticTacToeGame.model;

public class PlayerVsPlayerGame extends Game {

    private static final String PLAYER_2_NAME = "user_2";

    public PlayerVsPlayerGame() {
        super(PLAYER_2_NAME);
    }

    @Override
    public Game winningCheckAndMakingMove(int cellNumber) {
        if (cells.get(cellNumber).equals(getEmptyCell())) {
            cells.put(cellNumber, getCurrentPlayer());
        } else {
            setIncorrectMove(true);
            return this;
        }

        if (isPlayerWin(getCurrentPlayer())) {
            if (getCurrentPlayer().equals(getPlayer1())) {
                setPlayer1win(true);
            } else {
                setPlayer2win(true);
            }
            switchCurrentPlayer();
            return this;
        }

        if (isDraw()) {
            setDraw(true);
            return this;
        }

        switchCurrentPlayer();
        return this;
    }
}