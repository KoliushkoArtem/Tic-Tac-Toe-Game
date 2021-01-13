package com.neven.ticTacToeGame.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class {@code Game} is an abstract class which contain main logic for the games. All child classes should realize 2
 * abstract methods({@link Game#makeFirstMove()}, {@link Game#makeMove()}), realization of this methods should
 * be depended on type of the current game in which this method is implemented.
 * <p>Main logical method of the games is {@link Game#winningCheckAndMakingMove(int)}</p>
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 */
@Data
public abstract class Game {

    /**
     * Field with a name of the player(always user) which uses to fill the game field and checking wins.
     */
    private final String player1 = "user_1";
    /**
     * Field with a name of the player, depend on type of the game and initialized in constructor,
     * which uses to fill the game field and checking wins.
     */
    private final String player2;
    /**
     * Field with a name of the  empty cell on the game field which uses to fill the game field
     * and checking draws and making moves.
     */
    private final String emptyCell = "empty";
    /**
     * Field which contain {@code String} value of the current user.<br/>Has access level "PROTECTED" for setter.
     */
    @Setter(value = AccessLevel.PROTECTED)
    private String currentPlayer;
    /**
     * Field which contain {@code Boolean} value is the user is win.<br/>Default value "false".
     * <br/>Has access level "PROTECTED" for setter.
     */
    @Setter(value = AccessLevel.PROTECTED)
    private boolean player1win = false;
    /**
     * Field which contain {@code Boolean} value is the player 2 is win.<br/>Default value "false".
     * <br/>Has access level "PROTECTED" for setter.
     */
    @Setter(value = AccessLevel.PROTECTED)
    private boolean player2win = false;
    /**
     * Field which contain {@code Boolean} value is the game is finished and game has no winner.
     * <br/>Default value "false".<br/>Has access level "PROTECTED" for setter.
     */
    @Setter(value = AccessLevel.PROTECTED)
    private boolean draw = false;
    /**
     * Field which contain {@code Boolean} value is the move made by user is incorrect.
     * <br/>Default value "false".<br/>Has access level "PROTECTED" for setter.
     */
    @Setter(value = AccessLevel.PROTECTED)
    private boolean incorrectMove = false;
    /**
     * Field with {@code Map<Integer, String>} value, is the {@link java.util.HashMap} which contain
     * game field cells.<br/>Firs param "{@code Integer}" - is the number of game field (proper values 1-9).
     * <br/>Second param "{@code String}" - this is param which contain an information about value of game field
     * (proper values {@link Game#player1}, {@link Game#player2}, {@link Game#emptyCell})
     * <br/>Has access level "PRIVATE" for setter.
     */
    @Setter(value = AccessLevel.PRIVATE)
    private Map<Integer, String> cells = new TreeMap<>();

    /**
     * Constructor for class {@link Game}.<br/>Constructor initialized {@link Game#player2} field by incoming param,
     * {@link Game#currentPlayer} field by {@link Game#player1} value and {@link Game#cells} by filling all game filed
     * with {@link Game#emptyCell}
     *
     * @param player_2 {@code String} value of the second player(first always user) which depend on type of the created game.
     */
    public Game(String player_2) {
        this.player2 = player_2;
        this.currentPlayer = player1;

        for (int i = 1; i <= 9; i++) {
            cells.put(i, emptyCell);
        }
    }

    /**
     * This method is the main logical method of the game.
     * <br/>The method is accept incoming param(user move) and check if this game field cell is empty:
     * <br/>&emsp;-If No, {@link Game#isIncorrectMove()} will be set to "true" and game will be returned.
     * <br/>&emsp;-If Yes, sell will be filled by the current user.
     * <br/>Then method will call {@link Game#isPlayerWin(String currentPlayer)} method for check is user does win:
     * <br/>&emsp;-If Yes, current user will be set as winner and game will be returned.
     * <br/>Then method will call {@link Game#isItDraw()} method to check did the user move was the last empty game field
     * in this game:
     * <br/>&emsp;-If Yes, {@link Game#draw} will be set to "true" and game will be returned.
     * <br/>Then method depend no type of the game will:
     * <br/>&emsp;-If game type is {@link PlayerVsRandomGame}, method {@link Game#switchCurrentPlayer()} will be called
     * and game will be returned.
     * <br/>&emsp;-In all another cases current user will be switched({@link Game#switchCurrentPlayer()}) and game will
     * make a move({@link Game#makeMove()}), then methods {@link Game#isPlayerWin(String currentPlayer)} and
     * {@link Game#isItDraw()} will call(if any of this methods will return "true" value, result of the game will
     * be set and game will be returned), then current user will be switched({@link Game#switchCurrentPlayer()})
     * again and game will be returned.
     *
     * @param cellNumber {@code Integer} value(acceptable 1-9) which contain a number of the game field cell
     *                   where the user want to make a move.
     * @return {@link Game} with a user move and second player move(second player depend on type of the game).
     * If the game will be finished, returned {@link Game} also will contain the result of the game.
     */
    public Game winningCheckAndMakingMove(int cellNumber) {
        if (cells.get(cellNumber).equals(getEmptyCell())) {
            cells.put(cellNumber, getCurrentPlayer());
        } else {
            setIncorrectMove(true);
            return this;
        }

        if (isPlayerWin(currentPlayer)) {
            setWinner();
            return this;
        }

        if (isItDraw()) {
            setDraw(true);
            return this;
        }

        if (this instanceof PlayerVsPlayerGame) {
            makeMove();
        } else {
            switchCurrentPlayer();
            makeMove();

            if (isPlayerWin(currentPlayer)) {
                setWinner();
                return this;
            }

            if (isItDraw()) {
                setDraw(true);
                return this;
            }

            switchCurrentPlayer();
        }

        return this;
    }

    /**
     * The method is switch the current player. Changing depends on {@link Game#currentPlayer},
     * if {@link Game#currentPlayer} equals to {@link Game#player1} the method set {@link Game#currentPlayer} as
     * {@link Game#player2}, and if {@link Game#currentPlayer} equals to {@link Game#player2} the method set
     * {@link Game#currentPlayer} as {@link Game#player1}
     */
    protected void switchCurrentPlayer() {
        if (currentPlayer.equals(player1)) {
            setCurrentPlayer(player2);
        } else {
            setCurrentPlayer(player1);
        }
    }

    /**
     * The method check is the user with name incoming as the parameter of the method has complete the line(in classic
     * Tic Tac Toe game has 8 different lines, each of that lines consist from 3 game field cells). Teh method set
     * for each line {@code Boolean} result if the each cell of this line is equals to user with name incoming
     * as the parameter of the method, then returned all the results.
     *
     * @param player {@code String} value of the name of the user, to check is this user is win.
     * @return {@code Boolean} value is the user with name incoming as the parameter of the method has complete
     * any of 8 possible lines to win.
     */
    protected boolean isPlayerWin(@NotNull String player) {
        boolean line_1 = cells.get(1).equals(player) && cells.get(2).equals(player) && cells.get(3).equals(player);
        boolean line_2 = cells.get(4).equals(player) && cells.get(5).equals(player) && cells.get(6).equals(player);
        boolean line_3 = cells.get(7).equals(player) && cells.get(8).equals(player) && cells.get(9).equals(player);
        boolean line_4 = cells.get(1).equals(player) && cells.get(4).equals(player) && cells.get(7).equals(player);
        boolean line_5 = cells.get(2).equals(player) && cells.get(5).equals(player) && cells.get(8).equals(player);
        boolean line_6 = cells.get(3).equals(player) && cells.get(6).equals(player) && cells.get(9).equals(player);
        boolean line_7 = cells.get(1).equals(player) && cells.get(5).equals(player) && cells.get(9).equals(player);
        boolean line_8 = cells.get(3).equals(player) && cells.get(5).equals(player) && cells.get(7).equals(player);

        return line_1 || line_2 || line_3 || line_4 || line_5 || line_6 || line_7 || line_8;
    }

    /**
     * The method set current user as a winner.
     */
    private void setWinner() {
        if (currentPlayer.equals(player1)) {
            setPlayer1win(true);
        }

        if (currentPlayer.equals(player2)) {
            setPlayer2win(true);
        }
    }

    /**
     * The method check this {@link Game} has no winner and game field has no {@link Game#emptyCell} left.
     *
     * @return {@code Boolean} value if the {@link Game} has the {@link Game#draw}.
     */
    private boolean isItDraw() {
        return !isPlayerWin(player1) && !isPlayerWin(player2) && !cells.containsValue(emptyCell);
    }

    /**
     * The abstract method should consist the logic how exactly the extended {@link Game} should make first move.
     * <br/>Logically first move should be made by {@link Game#player2}.
     *
     * @return the {@link Game} with a first move made by {@link Game#player2}.
     */
    public abstract Game makeFirstMove();

    /**
     * The abstract method should consist the logic how exactly the extended {@link Game} should make move.
     * <br/>Logically move should be made by {@link Game#player2} and the result of the method should an empty cell
     * on game field filled by {@link Game#player2}.
     * <br/>!!! Not for Player versus Player games like {@link PlayerVsPlayerGame}, logically in this case the method
     * should just call the {@link Game#switchCurrentPlayer()} method.
     */
    protected abstract void makeMove();
}