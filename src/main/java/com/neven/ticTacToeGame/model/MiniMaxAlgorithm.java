package com.neven.ticTacToeGame.model;

import java.util.Map;

/**
 * The {@code MiniMaxAlgorithm} class is a class which contain The Algorithm MiniMax and methods that allow to
 * The Algorithm MiniMax make an analysis of the game field, evaluate any possible future moves of algorithm and user
 * and make a chose what move will be less harmful for the result of the game. Best result of the game will be
 * The Algorithm MiniMax winning, so The Algorithm MiniMax always will try to win and less harmful for the result
 * of the game wil be the draw.
 * <br>As base for this class was <a href="https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/">this</a>
 * article and code from examples. Code was refactored for current application needs.
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 * @see <a href="https://en.wikipedia.org/wiki/Minimax">MiniMax on Wikipedia</a>
 * @see <a href="https://www.baeldung.com/java-minimax-algorithm">Introduction to Minimax Algorithm with a Java Implementation</a>
 * @see <a href="https://www.javatpoint.com/mini-max-algorithm-in-ai">Mini-Max Algorithm in Artificial Intelligence</a>
 */
public class MiniMaxAlgorithm {

    /**
     * Field with an injected in constructor {@link Game} class. This field used for take from game {@link Game#cells} and
     * evaluate game field cost for making best move currently for this {@link Game}.
     */
    private final Game game;
    /**
     * Field with character to mark a player on game field.
     */
    private final char player = 'p';
    /**
     * Field with character to mark an algorithm on game field.
     */
    private final char algorithm = 'a';
    /**
     * Field with character to mark empty a cell on game field.
     */
    private final char emptyCell = '_';

    /**
     * Constructor for class {@link MiniMaxAlgorithm}.<br>Constructor initialized {@link MiniMaxAlgorithm#game} field by
     * incoming param {@link PlayerVsAlgorithmGame} value.
     *
     * @param game {@link PlayerVsAlgorithmGame} in what should be made best algorithm move.
     */
    public MiniMaxAlgorithm(Game game) {
        this.game = game;
    }

    /**
     * This method takes game board from the {@link MiniMaxAlgorithm#game} by using method {@link MiniMaxAlgorithm#getBordFromGame()}
     * and for each empty cell on game field will called {@link MiniMaxAlgorithm#miniMax(char[][] gameField, int depth, Boolean isMax)}
     * method to figure out cost of this move if algorithm choose this cell for the best move. First empty cell which will
     * has more points will be returned as best move.
     *
     * @return {@code Integer} with a number of cell where an Algorithm MiniMax will made a move
     */
    public int findBestMove() {
        char[][] board = getBordFromGame();
        int bestVal = -1000;
        int row = -1;
        int col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == emptyCell) {
                    board[i][j] = algorithm;
                    int moveVal = miniMax(board, 0, false);
                    board[i][j] = emptyCell;
                    if (moveVal > bestVal) {
                        row = i;
                        col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        int bestMove = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bestMove++;
                if (row == i && col == j) {
                    return bestMove;
                }
            }
        }

        return bestMove;
    }

    /**
     * This method takes game board from the {@link MiniMaxAlgorithm#game} and refactor it from the line view to a classic view:
     * <br>Line view:<br>(1, 2, 3, 4, 5, 6, 7, 8, 9)<br>Classic view:<br><u>1</u>|<u>2</u>|<u>3</u><br><u>4</u>|<u>5</u>|<u>6</u><br>7|8|9
     * <br>All moves on game field also marked on the board by {@link MiniMaxAlgorithm#player},
     * {@link MiniMaxAlgorithm#algorithm}, {@link MiniMaxAlgorithm#emptyCell}) characters.
     *
     * @return {@code char[3][3]} with refactored game board and all moves made in the game.
     */
    private char[][] getBordFromGame() {
        char[][] boardToReturn = new char[3][3];
        Map<Integer, String> cells = game.getCells();
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                counter++;
                if (cells.get(counter).equals(game.getPlayer1())) {
                    boardToReturn[i][j] = player;
                } else if (cells.get(counter).equals(game.getPlayer2())) {
                    boardToReturn[i][j] = algorithm;
                } else {
                    boardToReturn[i][j] = emptyCell;
                }
            }
        }

        return boardToReturn;
    }

    /**
     * This method is a recursive method which evaluate all possible results of the game and return value of game board
     * (using method {@link MiniMaxAlgorithm#evaluate(char[][])}).The method call itself, increasing deepness
     * of the "tree" with all possible game combination, till game will be finished.
     *
     * @param board {@code char[][]} board which can be received by using a method {@link MiniMaxAlgorithm#getBordFromGame()}.
     * @param depth deepness of the "tree" with all possible game combination.
     * @param isMax {@code Boolean} value is the MiniMax Algorithm move turn or an user<br>(true - algorithm, false - user).
     *              <br>Also called as Maximizer and Minimizer.
     * @return evaluated cost of the game board, acceptable values {@code -10}, {@code +10}, {@code 0}(zero)
     * @see <a href="https://www.javatpoint.com/mini-max-algorithm-in-ai">Mini-Max Algorithm in Artificial Intelligence</a>
     */
    private int miniMax(char[][] board, int depth, Boolean isMax) {
        int score = evaluate(board);

        if (score == +10 || score == -10) {
            return score;
        }

        if (!isMovesLeft(board)) {
            return 0;
        }

        int best;
        if (isMax) {
            best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == emptyCell) {
                        board[i][j] = algorithm;
                        best = Math.max(best, miniMax(board, depth + 1, false));
                        board[i][j] = emptyCell;
                    }
                }
            }
        } else {
            best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == emptyCell) {
                        board[i][j] = player;
                        best = Math.min(best, miniMax(board, depth + 1, true));
                        board[i][j] = emptyCell;
                    }
                }
            }
        }

        return best;
    }

    /**
     * The method takes game board ang check if any of {@link MiniMaxAlgorithm#emptyCell} left on game field.
     *
     * @param board {@code char[][]} board which can be received by using a method {@link MiniMaxAlgorithm#getBordFromGame()}.
     * @return {@code Boolean} value is any {@link MiniMaxAlgorithm#emptyCell} left on game field.
     */
    private Boolean isMovesLeft(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == emptyCell) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * The method receive game board and check is any of winning lines was finished.
     * <br>According to the amount of the game field cells and rules of the game it can be only 8 lines on which
     * a player or the algorithm can make a winning combination from 3 moves in a row.
     * <br>According needs of the algorithm this method evaluate cost of the game field. If an user make a winning
     * line will be returned negative value({@code -10}), if an algorithm make a winning line will be returned positive
     * value({@code +10}), if no one makes a winning line will be returned {@code 0}(zero).
     *
     * @param board {@code char[][]} board which can be received by using a method {@link MiniMaxAlgorithm#getBordFromGame()}.
     * @return evaluated cost of the game board, acceptable values {@code -10}, {@code +10}, {@code 0}(zero)
     */
    private int evaluate(char[][] board) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == algorithm) {
                    return +10;
                } else if (board[row][0] == player) {
                    return -10;
                }
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == algorithm) {
                    return +10;
                } else if (board[0][col] == player) {
                    return -10;
                }
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == algorithm) {
                return +10;
            } else if (board[0][0] == player) {
                return -10;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == algorithm) {
                return +10;
            } else if (board[0][2] == player) {
                return -10;
            }
        }

        return 0;
    }
}