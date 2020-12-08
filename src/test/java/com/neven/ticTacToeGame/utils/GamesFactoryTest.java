package com.neven.ticTacToeGame.utils;

import com.neven.ticTacToeGame.model.Game;
import com.neven.ticTacToeGame.model.PlayerVsAlgorithmGame;
import com.neven.ticTacToeGame.model.PlayerVsPlayerGame;
import com.neven.ticTacToeGame.model.PlayerVsRandomGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamesFactoryTest {

    @Test
    @DisplayName(value = "When call getGame method with PLAYER_VS_PLAYER game type assert that return will be PlayerVsPlayerGame.class")
    void playerVsPlayer() {
        Game testGame = GamesFactory.getGame(GameTypes.PLAYER_VS_PLAYER);

        assertTrue(testGame instanceof PlayerVsPlayerGame);
    }

    @Test
    @DisplayName(value = "When call getGame method with PLAYER_VS_RANDOM game type assert that return will be PlayerVsRandomGame.class")
    void playerVsRandom() {
        Game testGame = GamesFactory.getGame(GameTypes.PLAYER_VS_RANDOM);

        assertTrue(testGame instanceof PlayerVsRandomGame);
    }

    @Test
    @DisplayName(value = "When call getGame method with PLAYER_VS_ALGORITHM game type assert that return will be PlayerVsAlgorithmGame.class")
    void playerVsAlgorithm() {
        Game testGame = GamesFactory.getGame(GameTypes.PLAYER_VS_ALGORITHM);

        assertTrue(testGame instanceof PlayerVsAlgorithmGame);
    }
}