package com.neven.ticTacToeGame.model;

import java.util.Random;

/**
 * Class {@code PlayerVsPlayerGame} extend {@link Game} class and implement his two abstract methods for games Player
 * versus Random(random generated moves).
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 */
public class PlayerVsRandomGame extends Game {

    /**
     * Constant with a name of the player2(in this case "Random"). Used in constructor for creation an object of the {@link Game}.
     */
    private static final String PLAYER_2_NAME = "random";

    /**
     * Constructor for class {@link PlayerVsRandomGame}.<br>Initialize parent({@link Game} class) constructor
     * with a name of second player.
     */
    public PlayerVsRandomGame() {
        super(PLAYER_2_NAME);
    }

    /**
     * This method overrides parent {@link Game#makeFirstMove()} method.
     * <br>Method just call {@link PlayerVsRandomGame#makeMove()} method and return current game.
     *
     * @return {@link Game} with randomly generated "Random" move.
     */
    @Override
    public Game makeFirstMove() {
        makeMove();
        return this;
    }

    /**
     * This method overrides parent {@link Game#makeMove()} method.
     * <br>This is recursive method, and it will be call its self till randomly generated value will be an empty cell.
     * This method use {@link Random} for generating random value within 1-9(amount of game field cells). Generated
     * value will be a number of game field cell to which put "Random" name value. this cell
     * wil be a "Random" move in the game.
     */
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