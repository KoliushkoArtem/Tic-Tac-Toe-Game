package com.neven.ticTacToeGame.controller;

import com.neven.ticTacToeGame.model.PlayerVsPlayerGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerVsPlayerControllerTest {

    private final PlayerVsPlayerController controller = new PlayerVsPlayerController();
    private final String sessionAttributeGameName = "game";
    private final Model modelMock = mock(Model.class);

    @Test
    @DisplayName(value = "When call startGameWithPlayers method assert that in returned model will be attribute PlayerVsPlayerGame.class with key \"game\"")
    void startGameWithPlayers() {
        ModelAndView modelAndViewToTest = controller.startGameWithPlayers();
        ModelMap resultModelMap = modelAndViewToTest.getModelMap();

        assertTrue(resultModelMap.containsKey(sessionAttributeGameName));
        assertEquals(new PlayerVsPlayerGame(), resultModelMap.get(sessionAttributeGameName));
    }

    @Test
    @DisplayName(value = "When game() method with no session attribute \"game\" assert that in returned model will be attribute PlayerVsPlayerGame.class with key \\\"game\\\"\"")
    void gameFailWithNoSessionAttribute() {
        when(modelMock.getAttribute(sessionAttributeGameName)).thenReturn(null);
        ModelAndView modelAndViewToTest = controller.game(modelMock, 1);
        ModelMap resultModelMap = modelAndViewToTest.getModelMap();

        assertTrue(resultModelMap.containsKey(sessionAttributeGameName));
        assertEquals(new PlayerVsPlayerGame(), resultModelMap.get(sessionAttributeGameName));
    }

    @Test
    @DisplayName(value = "When game() method with no session attribute \"game\" assert that in returned model will be attribute PlayerVsPlayerGame.class with key \\\"game\\\"\"")
    void gameSuccessMove() {
        PlayerVsPlayerGame playerVsPlayerGameToTest = new PlayerVsPlayerGame();
        assertEquals(playerVsPlayerGameToTest.getEmptyCell(), playerVsPlayerGameToTest.getCells().get(1));
        when(modelMock.getAttribute(sessionAttributeGameName)).thenReturn(playerVsPlayerGameToTest);
        ModelAndView modelAndViewToTest = controller.game(modelMock, 1);
        ModelMap resultModelMap = modelAndViewToTest.getModelMap();

        assertTrue(resultModelMap.containsKey(sessionAttributeGameName));
    }
}