package com.neven.ticTacToeGame.model;

import java.util.Random;

public class PlayerVsAlgorithmGame extends Game {

    private static final String PLAYER_2_NAME = "MiniMax";

    public PlayerVsAlgorithmGame() {
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

        makeMove();

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
        int firstMove = new Random().nextInt(4) + 1;
        switch (firstMove) {
            case 1:
                getCells().put(1, getPlayer2());
                return this;
            case 2:
                getCells().put(3, getPlayer2());
                return this;
            case 3:
                getCells().put(7, getPlayer2());
                return this;
            case 4:
                getCells().put(9, getPlayer2());
                return this;
            default:
                return this;
        }
    }

    private void makeMove() {
        MiniMaxAlgorithm algorithm = new MiniMaxAlgorithm(this);
        int bestMove = algorithm.findBestMove();
        if (cells.get(bestMove).equals(getEmptyCell())) {
            cells.put(bestMove, getPlayer2());
        } else {
            setIncorrectMove(true);
        }
    }
}
