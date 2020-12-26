package com.neven.ticTacToeGame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MiniMaxAlgorithmTest {

    private final Game gameMock = mock(PlayerVsAlgorithmGame.class);
    private final MiniMaxAlgorithm algorithm = new MiniMaxAlgorithm(gameMock);
    private final String playerCell = new PlayerVsAlgorithmGame().getPlayer1();
    private final String algorithmCell = new PlayerVsAlgorithmGame().getPlayer2();
    private final String emptyCell = new PlayerVsAlgorithmGame().getEmptyCell();
    private final Map<Integer, String> testCells = new TreeMap<>();

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 9; i++) {
            testCells.put(i, emptyCell);
        }
    }

    @Test
    @DisplayName(value = "When call findBestMove() method, assert that move will avoid player winning on line 1 (1, 2, 3 cells)")
    void avoidPlayerWinningOpportunityLine1() {
        when(gameMock.getPlayer1()).thenReturn(playerCell);
        when(gameMock.getPlayer2()).thenReturn(algorithmCell);
        when(gameMock.getEmptyCell()).thenReturn(emptyCell);
        testCells.put(5, algorithmCell);
        testCells.put(1, playerCell);
        testCells.put(2, playerCell);

        when(gameMock.getCells()).thenReturn(testCells);

        assertEquals(3, algorithm.findBestMove());

        testCells.put(1, emptyCell);
        testCells.put(3, playerCell);

        assertEquals(1, algorithm.findBestMove());

        testCells.put(1, playerCell);
        testCells.put(2, emptyCell);

        assertEquals(2, algorithm.findBestMove());
    }

    @Test
    @DisplayName(value = "When call findBestMove() method, assert that move will avoid player winning on line 2 (4, 5, 6 cells)")
    void avoidPlayerWinningOpportunityLine2() {
        when(gameMock.getPlayer1()).thenReturn(playerCell);
        when(gameMock.getPlayer2()).thenReturn(algorithmCell);
        when(gameMock.getEmptyCell()).thenReturn(emptyCell);
        testCells.put(1, algorithmCell);
        testCells.put(4, playerCell);
        testCells.put(5, playerCell);

        when(gameMock.getCells()).thenReturn(testCells);

        assertEquals(6, algorithm.findBestMove());

        testCells.put(4, emptyCell);
        testCells.put(6, playerCell);

        assertEquals(4, algorithm.findBestMove());

        testCells.put(4, playerCell);
        testCells.put(5, emptyCell);

        assertEquals(5, algorithm.findBestMove());
    }

    @Test
    @DisplayName(value = "When call findBestMove() method, assert that move will avoid player winning on line 3 (7, 8, 9 cells)")
    void avoidPlayerWinningOpportunityLine3() {
        when(gameMock.getPlayer1()).thenReturn(playerCell);
        when(gameMock.getPlayer2()).thenReturn(algorithmCell);
        when(gameMock.getEmptyCell()).thenReturn(emptyCell);
        testCells.put(5, algorithmCell);
        testCells.put(7, playerCell);
        testCells.put(8, playerCell);

        when(gameMock.getCells()).thenReturn(testCells);

        assertEquals(9, algorithm.findBestMove());

        testCells.put(7, emptyCell);
        testCells.put(9, playerCell);

        assertEquals(7, algorithm.findBestMove());

        testCells.put(7, playerCell);
        testCells.put(8, emptyCell);

        assertEquals(8, algorithm.findBestMove());
    }

    @Test
    @DisplayName(value = "When call findBestMove() method, assert that move will avoid player winning on line 4 (1, 4, 7 cells)")
    void avoidPlayerWinningOpportunityLine4() {
        when(gameMock.getPlayer1()).thenReturn(playerCell);
        when(gameMock.getPlayer2()).thenReturn(algorithmCell);
        when(gameMock.getEmptyCell()).thenReturn(emptyCell);
        testCells.put(5, algorithmCell);
        testCells.put(1, playerCell);
        testCells.put(4, playerCell);

        when(gameMock.getCells()).thenReturn(testCells);

        assertEquals(7, algorithm.findBestMove());

        testCells.put(1, emptyCell);
        testCells.put(7, playerCell);

        assertEquals(1, algorithm.findBestMove());

        testCells.put(1, playerCell);
        testCells.put(4, emptyCell);

        assertEquals(4, algorithm.findBestMove());
    }

    @Test
    @DisplayName(value = "When call findBestMove() method, assert that move will avoid player winning on line 5 (2, 5, 8 cells)")
    void avoidPlayerWinningOpportunityLine5() {
        when(gameMock.getPlayer1()).thenReturn(playerCell);
        when(gameMock.getPlayer2()).thenReturn(algorithmCell);
        when(gameMock.getEmptyCell()).thenReturn(emptyCell);
        testCells.put(1, algorithmCell);
        testCells.put(2, playerCell);
        testCells.put(5, playerCell);

        when(gameMock.getCells()).thenReturn(testCells);

        assertEquals(8, algorithm.findBestMove());

        testCells.put(2, emptyCell);
        testCells.put(8, playerCell);

        assertEquals(2, algorithm.findBestMove());

        testCells.put(2, playerCell);
        testCells.put(5, emptyCell);

        assertEquals(5, algorithm.findBestMove());
    }

    @Test
    @DisplayName(value = "When call findBestMove() method, assert that move will avoid player winning on line 6 (3, 6, 9 cells)")
    void avoidPlayerWinningOpportunityLine6() {
        when(gameMock.getPlayer1()).thenReturn(playerCell);
        when(gameMock.getPlayer2()).thenReturn(algorithmCell);
        when(gameMock.getEmptyCell()).thenReturn(emptyCell);
        testCells.put(5, algorithmCell);
        testCells.put(3, playerCell);
        testCells.put(6, playerCell);

        when(gameMock.getCells()).thenReturn(testCells);

        assertEquals(9, algorithm.findBestMove());

        testCells.put(3, emptyCell);
        testCells.put(9, playerCell);

        assertEquals(3, algorithm.findBestMove());

        testCells.put(3, playerCell);
        testCells.put(6, emptyCell);

        assertEquals(6, algorithm.findBestMove());
    }

    @Test
    @DisplayName(value = "When call findBestMove() method, assert that move will avoid player winning on line 7 (1, 5, 9 cells)")
    void avoidPlayerWinningOpportunityLine7() {
        when(gameMock.getPlayer1()).thenReturn(playerCell);
        when(gameMock.getPlayer2()).thenReturn(algorithmCell);
        when(gameMock.getEmptyCell()).thenReturn(emptyCell);
        testCells.put(2, algorithmCell);
        testCells.put(3, algorithmCell);
        testCells.put(1, playerCell);
        testCells.put(5, playerCell);

        when(gameMock.getCells()).thenReturn(testCells);

        assertEquals(9, algorithm.findBestMove());

        testCells.put(1, emptyCell);
        testCells.put(9, playerCell);

        assertEquals(1, algorithm.findBestMove());

        testCells.put(1, playerCell);
        testCells.put(5, emptyCell);

        assertEquals(5, algorithm.findBestMove());
    }

    @Test
    @DisplayName(value = "When call findBestMove() method, assert that move will avoid player winning on line 8 (3, 5, 7 cells)")
    void avoidPlayerWinningOpportunityLine8() {
        when(gameMock.getPlayer1()).thenReturn(playerCell);
        when(gameMock.getPlayer2()).thenReturn(algorithmCell);
        when(gameMock.getEmptyCell()).thenReturn(emptyCell);
        testCells.put(1, algorithmCell);
        testCells.put(2, algorithmCell);
        testCells.put(3, playerCell);
        testCells.put(5, playerCell);

        when(gameMock.getCells()).thenReturn(testCells);

        assertEquals(7, algorithm.findBestMove());

        testCells.put(3, emptyCell);
        testCells.put(7, playerCell);

        assertEquals(3, algorithm.findBestMove());

        testCells.put(3, playerCell);
        testCells.put(5, emptyCell);

        assertEquals(5, algorithm.findBestMove());
    }
}