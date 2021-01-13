package com.neven.ticTacToeGame.model;

import java.util.Random;

/**
 * Class {@code PlayerVsAlgorithmGame} extend {@link Game} class and implement his two abstract methods for game with
 * an Algorithm MiniMax({@link MiniMaxAlgorithm}).
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 */
public class PlayerVsAlgorithmGame extends Game {

    /**
     * Constant with a name of the player2(in this case an Algorithm MiniMax). Used in constructor for creation
     * an object of the {@link Game}.
     */
    private static final String PLAYER_2_NAME = "MiniMax";

    /**
     * Constructor for class {@link PlayerVsAlgorithmGame}.<br>Initialize parent({@link Game} class) constructor
     * with a name of second player.
     */
    public PlayerVsAlgorithmGame() {
        super(PLAYER_2_NAME);

    }

    /**
     * This method overrides parent {@link Game#makeFirstMove()} method.
     * <br>According to the {@link MiniMaxAlgorithm} specific, it is always return as {@link MiniMaxAlgorithm#findBestMove()}
     * result first positive value of the {@link MiniMaxAlgorithm} move, if the {@link MiniMaxAlgorithm} will be make
     * a move in an empty game field it is always will be first cell. So for making first move more variable,
     * this method returns randomly chosen one of the 4 starting positions(corners of the game field).
     *
     * @return {@link PlayerVsPlayerGame} with a first move of the algorithm.
     */
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

    /**
     * This method overrides parent {@link Game#makeMove()} method.
     * <br>Method create an object of the {@link MiniMaxAlgorithm} class, and call his {@link MiniMaxAlgorithm#findBestMove()}
     * method to receive number of game field cell to which put the algorithm name value. This cell
     * will be a {@link MiniMaxAlgorithm} move in the game.
     */
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