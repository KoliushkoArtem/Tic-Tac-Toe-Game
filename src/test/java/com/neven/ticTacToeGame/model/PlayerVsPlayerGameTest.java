package com.neven.ticTacToeGame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerVsPlayerGameTest {

    private Game testGame;

    @BeforeEach
    void setUp() {
        this.testGame = new PlayerVsPlayerGame();
    }

    @Test
    @DisplayName(value = "When call winningCheckAndMakingMove method with empty cell number to make a move assert that incorrectMove will be false and cell will be filled by current player")
    void whenMoveIsCorrect() {
        String currentPlayer = testGame.getCurrentPlayer();
        assertFalse(testGame.isIncorrectMove());
        Game result = testGame.winningCheckAndMakingMove(1);

        assertFalse(result.isIncorrectMove());
        assertEquals(currentPlayer, testGame.getCells().get(1));
    }

    @Test
    @DisplayName(value = "When call winningCheckAndMakingMove method with busy cell number to make a move assert that incorrectMove will be true")
    void whenMoveIsIncorrect() {
        assertFalse(testGame.isIncorrectMove());
        Game result = testGame.winningCheckAndMakingMove(1);
        result = result.winningCheckAndMakingMove(1);

        assertTrue(result.isIncorrectMove());
    }

    @Test
    @DisplayName(value = "When call winningCheckAndMakingMove method and player 1 win")
    void player1Win() {
        String player1 = testGame.getPlayer1();
        testGame.setCurrentPlayer(player1);
        testGame.getCells().put(1, player1);
        testGame.getCells().put(2, player1);
        assertFalse(testGame.isPlayer1win());

        Game resultGame = testGame.winningCheckAndMakingMove(3);

        assertTrue(resultGame.isPlayer1win());
    }

    @Test
    @DisplayName(value = "When call winningCheckAndMakingMove method and player 2 win")
    void player2Win() {
        String player2 = testGame.getPlayer2();
        testGame.setCurrentPlayer(player2);
        testGame.getCells().put(1, player2);
        testGame.getCells().put(2, player2);
        assertFalse(testGame.isPlayer2win());

        Game resultGame = testGame.winningCheckAndMakingMove(3);

        assertTrue(resultGame.isPlayer2win());
    }

    @Test
    @DisplayName(value = "When call winningCheckAndMakingMove method with last move and no one win assert that isDraw will be true")
    void isDraw() {
        testGame.getCells().put(1, testGame.getPlayer1());
        testGame.getCells().put(2, testGame.getPlayer1());
        testGame.getCells().put(3, testGame.getPlayer2());
        testGame.getCells().put(4, testGame.getPlayer2());
        testGame.getCells().put(5, testGame.getPlayer2());
        testGame.getCells().put(6, testGame.getPlayer1());
        testGame.getCells().put(7, testGame.getPlayer1());
        testGame.getCells().put(8, testGame.getPlayer1());
        testGame.setCurrentPlayer(testGame.getPlayer2());

        assertFalse(testGame.isPlayer1win());
        assertFalse(testGame.isPlayer2win());
        assertFalse(testGame.isDraw());

        Game resultGame = testGame.winningCheckAndMakingMove(9);

        assertFalse(resultGame.isPlayer1win());
        assertFalse(resultGame.isPlayer2win());
        assertTrue(resultGame.isDraw());
    }

    @Test
    @DisplayName(value = "When call makeFirstMove medthod assert that corrent player will br changed and game will be returned")
    void makeFirstMove() {
        assertEquals(testGame.getPlayer1(), testGame.getCurrentPlayer());

        Game gameResult = testGame.makeFirstMove();

        assertEquals(testGame.getPlayer2(), testGame.getCurrentPlayer());
    }
}