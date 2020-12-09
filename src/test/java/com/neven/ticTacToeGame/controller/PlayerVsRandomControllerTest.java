package com.neven.ticTacToeGame.controller;

import com.neven.ticTacToeGame.model.PlayerVsRandomGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerVsRandomControllerTest {

    private final PlayerVsRandomController controller = new PlayerVsRandomController();
    private final String sessionAttributeGameName = "game";
    private final Model modelMock = mock(Model.class);

    @Test
    @DisplayName(value = "When call etGameWithRandom method assert that returned ModelAndView will have \"randomStart\" view")
    void getGameWithRandom() {
        assertEquals("randomStart", controller.getGameWithRandom().getViewName());
    }

    @Test
    @DisplayName(value = "When call startGameWithRandom method with \"user\" parameter assert that returned ModelAndView will have game field with only empty fields and have \"random\" view")
    void startGameWithRandomUserStart() {
        ModelAndView result = controller.startGameWithRandom("user");

        assertTrue(result.getModelMap().containsAttribute(sessionAttributeGameName));

        Object o = result.getModelMap().get(sessionAttributeGameName);
        assertTrue(o instanceof PlayerVsRandomGame);

        PlayerVsRandomGame gameResult = (PlayerVsRandomGame) o;

        assertFalse(gameResult.getCells().containsValue(gameResult.getPlayer1()));
        assertFalse(gameResult.getCells().containsValue(gameResult.getPlayer2()));
        assertTrue(gameResult.getCells().containsValue(gameResult.getEmptyCell()));

    }

    @Test
    @DisplayName(value = "When call startGameWithRandom method with \"random\" parameter assert that returned ModelAndView will have game field will have \"random\" field and have \"random\" view")
    void startGameWithRandomRandomStart() {
        ModelAndView result = controller.startGameWithRandom("random");

        assertTrue(result.getModelMap().containsAttribute(sessionAttributeGameName));

        Object o = result.getModelMap().get(sessionAttributeGameName);
        assertTrue(o instanceof PlayerVsRandomGame);

        PlayerVsRandomGame gameResult = (PlayerVsRandomGame) o;

        assertFalse(gameResult.getCells().containsValue(gameResult.getPlayer1()));
        assertTrue(gameResult.getCells().containsValue(gameResult.getPlayer2()));
        assertTrue(gameResult.getCells().containsValue(gameResult.getEmptyCell()));
    }

    @Test
    @DisplayName(value = "When call startGameWithRandom method with incorrect parameter assert that returned ModelAndView will have \"randomStart\" view")
    void startGameWithRandomFailByWrongParam() {
        ModelAndView result = controller.startGameWithRandom("error");
        assertEquals("randomStart", result.getViewName());
    }

    @Test
    @DisplayName(value = "When game() method with session attribute \"game\" assert that in returned model will be attribute PlayerVsRandomGame.class with key \"game\" and player and random moves will be done")
    void game() {
        PlayerVsRandomGame gameToTest = new PlayerVsRandomGame();
        when(modelMock.getAttribute(sessionAttributeGameName)).thenReturn(gameToTest);

        ModelAndView result = controller.game(modelMock, 1);

        assertEquals("random", result.getViewName());

        Object o = result.getModelMap().get(sessionAttributeGameName);
        assertTrue(o instanceof PlayerVsRandomGame);
        PlayerVsRandomGame gameResult = (PlayerVsRandomGame) o;

        assertEquals(gameResult.getPlayer1(), gameResult.getCells().get(1));
        assertTrue(gameResult.getCells().containsValue(gameResult.getPlayer2()));
        assertTrue(gameResult.getCells().containsValue(gameResult.getEmptyCell()));

    }

    @Test
    @DisplayName(value = "When game() method with no session attribute \"game\" assert that in returned modelAndView will have view \"randomStart\"")
    void gameFailWithNoSessionAttribute() {
        when(modelMock.getAttribute(sessionAttributeGameName)).thenReturn(null);
        ModelAndView modelAndViewToTest = controller.game(modelMock, 1);

        assertEquals("randomStart", modelAndViewToTest.getViewName());
    }
}