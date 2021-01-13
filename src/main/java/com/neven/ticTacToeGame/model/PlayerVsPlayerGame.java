package com.neven.ticTacToeGame.model;

/**
 * Class {@code PlayerVsPlayerGame} extend {@link Game} class and implement his two abstract methods for games Player
 * versus Player.
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 */
public class PlayerVsPlayerGame extends Game {

    /**
     * Constant with a name of the player2(in this case an second user for games Player versus Player).
     * Used in constructor for creation an object of the {@link Game}.
     */
    private static final String PLAYER_2_NAME = "user_2";

    /**
     * Constructor for class {@link PlayerVsPlayerGame}.<br/>Initialize parent({@link Game} class) constructor
     * with a name of second player.
     */
    public PlayerVsPlayerGame() {
        super(PLAYER_2_NAME);
    }

    /**
     * This method overrides parent {@link Game#makeFirstMove()} method.
     * <br/>This method just switch current player using {@link Game#switchCurrentPlayer()} method and return current {@link Game}.
     * <br/>Games Player versus Player does not have any logic for making moves.
     *
     * @return {@link Game} with switched current player.
     */
    @Override
    public Game makeFirstMove() {
        switchCurrentPlayer();
        return this;
    }

    /**
     * This method overrides parent {@link Game#makeMove()} method.
     * <br/>This method just switch current player using {@link Game#switchCurrentPlayer()} method.
     * <br/>Games Player versus Player does not have any logic for making moves.
     */
    @Override
    protected void makeMove() {
        switchCurrentPlayer();
    }
}