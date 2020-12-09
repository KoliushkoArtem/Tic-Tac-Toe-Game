package com.neven.ticTacToeGame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game testGame;

    @BeforeEach
    void setUp() {
        testGame = new Game("test_Player_2") {
            @Override
            public Game winningCheckAndMakingMove(int cellNumber) {
                return null;
            }

            @Override
            public Game makeFirstMove() {
                return null;
            }
        };
    }

    @Test
    @DisplayName(value = "When call switchCurrentPlayer with currentPlayer player_1 assert that currentPlayer will be changed to player_2")
    void switchCurrentPlayer1() {
        testGame.setCurrentPlayer(testGame.getPlayer1());
        assertEquals(testGame.getPlayer1(), testGame.getCurrentPlayer());

        testGame.switchCurrentPlayer();

        assertEquals(testGame.getPlayer2(), testGame.getCurrentPlayer());
    }

    @Test
    @DisplayName(value = "When call switchCurrentPlayer with currentPlayer player_2 assert that currentPlayer will be changed to player_1")
    void switchCurrentPlayer2() {
        testGame.setCurrentPlayer(testGame.getPlayer2());
        assertEquals(testGame.getPlayer2(), testGame.getCurrentPlayer());

        testGame.switchCurrentPlayer();

        assertEquals(testGame.getPlayer1(), testGame.getCurrentPlayer());
    }

    @Test
    @DisplayName(value = "When call isDraw method with one or more empty cells assert that method return false")
    void isDrawFalse() {
        testGame.getCells().forEach((k, v) -> testGame.getCells().put(k, testGame.getCurrentPlayer()));
        testGame.getCells().put(1, testGame.getEmptyCell());

        assertFalse(testGame.isDraw());
    }

    @Test
    @DisplayName(value = "When call isDraw method with no empty cells assert that method return true")
    void isDrawTrue() {
        assertFalse(testGame.isDraw());
        testGame.getCells().forEach((k, v) -> testGame.getCells().put(k, testGame.getCurrentPlayer()));

        assertTrue(testGame.isDraw());
    }

    @Test
    @DisplayName("When call isPlayerWin method with no correct filled lines assert method return false")
    void isPlayerFail() {
        assertFalse(testGame.isPlayerWin(testGame.getPlayer1()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer2()));
    }

    @Test
    @DisplayName("When call isPlayerWin method with correct filled line 1 assert method return true")
    void isPlayerWinLine1() {
        putWinningLineInACells(1, 2, 3, testGame.getPlayer1());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer1()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer2()));

        testGame.setPlayer1win(false);
        putWinningLineInACells(1, 2, 3, testGame.getPlayer2());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer2()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer1()));
    }

    @Test
    @DisplayName("When call isPlayerWin method with correct filled line 1 assert method return true")
    void isPlayerWinLine2() {
        putWinningLineInACells(4, 5, 6, testGame.getPlayer1());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer1()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer2()));

        testGame.setPlayer1win(false);
        putWinningLineInACells(4, 5, 6, testGame.getPlayer2());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer2()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer1()));
    }

    @Test
    @DisplayName("When call isPlayerWin method with correct filled line 1 assert method return true")
    void isPlayerWinLine3() {
        putWinningLineInACells(7, 8, 9, testGame.getPlayer1());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer1()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer2()));

        testGame.setPlayer1win(false);
        putWinningLineInACells(7, 8, 9, testGame.getPlayer2());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer2()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer1()));
    }

    @Test
    @DisplayName("When call isPlayerWin method with correct filled line 1 assert method return true")
    void isPlayerWinLine4() {
        putWinningLineInACells(1, 4, 7, testGame.getPlayer1());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer1()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer2()));

        testGame.setPlayer1win(false);
        putWinningLineInACells(1, 4, 7, testGame.getPlayer2());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer2()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer1()));
    }

    @Test
    @DisplayName("When call isPlayerWin method with correct filled line 1 assert method return true")
    void isPlayerWinLine5() {
        putWinningLineInACells(2, 5, 8, testGame.getPlayer1());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer1()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer2()));

        testGame.setPlayer1win(false);
        putWinningLineInACells(2, 5, 8, testGame.getPlayer2());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer2()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer1()));
    }

    @Test
    @DisplayName("When call isPlayerWin method with correct filled line 1 assert method return true")
    void isPlayerWinLine6() {
        putWinningLineInACells(3, 6, 9, testGame.getPlayer1());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer1()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer2()));

        testGame.setPlayer1win(false);
        putWinningLineInACells(3, 6, 9, testGame.getPlayer2());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer2()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer1()));
    }

    @Test
    @DisplayName("When call isPlayerWin method with correct filled line 1 assert method return true")
    void isPlayerWinLine7() {
        putWinningLineInACells(1, 5, 9, testGame.getPlayer1());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer1()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer2()));

        testGame.setPlayer1win(false);
        putWinningLineInACells(1, 5, 9, testGame.getPlayer2());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer2()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer1()));
    }

    @Test
    @DisplayName("When call isPlayerWin method with correct filled line 1 assert method return true")
    void isPlayerWinLine8() {
        putWinningLineInACells(3, 5, 7, testGame.getPlayer1());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer1()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer2()));

        testGame.setPlayer1win(false);
        putWinningLineInACells(3, 5, 7, testGame.getPlayer2());

        assertTrue(testGame.isPlayerWin(testGame.getPlayer2()));
        assertFalse(testGame.isPlayerWin(testGame.getPlayer1()));
    }

    private void putWinningLineInACells(int cell1, int cell2, int cell3, String player) {
        testGame.getCells().put(cell1, player);
        testGame.getCells().put(cell2, player);
        testGame.getCells().put(cell3, player);
    }
}