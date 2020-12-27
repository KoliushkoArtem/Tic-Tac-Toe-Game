package com.neven.ticTacToeGame.model;

import java.util.Random;

public class PlayerVsAlgorithmGame extends Game {

    private static final String PLAYER_2_NAME = "MiniMax";

    public PlayerVsAlgorithmGame() {
        super(PLAYER_2_NAME);

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

    @Override
    protected void makeMove() {
        MiniMaxAlgorithm algorithm = new MiniMaxAlgorithm(this);
        int bestMove = algorithm.findBestMove();
        if (getCells().get(bestMove).equals(getEmptyCell())) {
            getCells().put(bestMove, getPlayer2());
        } else {
            setIncorrectMove(true);
        }
    }
}
