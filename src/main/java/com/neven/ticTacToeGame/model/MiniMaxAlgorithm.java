package com.neven.ticTacToeGame.model;

import java.util.Map;

public class MiniMaxAlgorithm {

    private final Game game;
    private final char player = 'p';
    private final char algorithm = 'a';
    private final char emptyCell = '_';


    public MiniMaxAlgorithm(Game game) {
        this.game = game;
    }

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

    private int evaluate(char[][] b) {
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
                if (b[row][0] == algorithm) {
                    return +10;
                } else if (b[row][0] == player) {
                    return -10;
                }
            }
        }

        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
                if (b[0][col] == algorithm) {
                    return +10;
                } else if (b[0][col] == player) {
                    return -10;
                }
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == algorithm) {
                return +10;
            } else if (b[0][0] == player) {
                return -10;
            }
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == algorithm) {
                return +10;
            } else if (b[0][2] == player) {
                return -10;
            }
        }

        return 0;
    }

    private int minimax(char[][] board, int depth, Boolean isMax) {
        int score = evaluate(board);

        if (score == 10) {
            return score;
        }

        if (score == -10) {
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
                        best = Math.max(best, minimax(board, depth + 1, false));
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
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board[i][j] = emptyCell;
                    }
                }
            }
        }

        return best;
    }

    public int findBestMove() {
        char[][] board = getBordFromGame();
        int bestVal = -1000;
        int row = -1;
        int col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == emptyCell) {
                    board[i][j] = algorithm;
                    int moveVal = minimax(board, 0, false);
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
}