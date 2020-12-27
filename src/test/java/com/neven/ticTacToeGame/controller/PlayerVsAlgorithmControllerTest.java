package com.neven.ticTacToeGame.controller;

import com.neven.ticTacToeGame.entity.Statistic;
import com.neven.ticTacToeGame.model.PlayerVsAlgorithmGame;
import com.neven.ticTacToeGame.service.StatisticService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerVsAlgorithmControllerTest {

    private final StatisticService statisticServiceMock = mock(StatisticService.class);
    private final PlayerVsAlgorithmController controller = new PlayerVsAlgorithmController(statisticServiceMock);
    private final String sessionAttributeGameName = "game";
    private final String sessionAttributeStatisticName = "statistic";
    private final Model modelMock = mock(Model.class);

    @Test
    @DisplayName(value = "When call etGameWithRandom method assert that returned ModelAndView will have \"algorithmStart\" view")
    void getGameWithAlgorithm() {
        assertEquals("algorithmStart", controller.getGameWithAlgorithm().getViewName());
    }

    @Test
    @DisplayName(value = "When call startGameWithAlgorithm method with \"user\" parameter assert that returned ModelAndView will have game field with only empty fields and have \"algorithm\" view")
    void startGameWithAlgorithmWhereUserStart() {
        ModelAndView result = controller.startGameWithAlgorithm("user");

        assertTrue(result.getModelMap().containsAttribute(sessionAttributeGameName));

        Object o = result.getModelMap().get(sessionAttributeGameName);
        assertTrue(o instanceof PlayerVsAlgorithmGame);

        PlayerVsAlgorithmGame gameResult = (PlayerVsAlgorithmGame) o;

        assertFalse(gameResult.getCells().containsValue(gameResult.getPlayer1()));
        assertFalse(gameResult.getCells().containsValue(gameResult.getPlayer2()));
        assertTrue(gameResult.getCells().containsValue(gameResult.getEmptyCell()));
        assertEquals(result.getViewName(), "algorithm");
    }

    @Test
    @DisplayName(value = "When call startGameWithAlgorithm method with \"algorithm\" parameter assert that returned ModelAndView will have game field with empty fields and one algorithm field and have \"algorithm\" view")
    void startGameWithAlgorithmWhereAlgorithmStart() {
        ModelAndView result = controller.startGameWithAlgorithm("algorithm");

        assertTrue(result.getModelMap().containsAttribute(sessionAttributeGameName));

        Object o = result.getModelMap().get(sessionAttributeGameName);
        assertTrue(o instanceof PlayerVsAlgorithmGame);

        PlayerVsAlgorithmGame gameResult = (PlayerVsAlgorithmGame) o;

        assertFalse(gameResult.getCells().containsValue(gameResult.getPlayer1()));
        assertTrue(gameResult.getCells().containsValue(gameResult.getPlayer2()));
        assertTrue(gameResult.getCells().containsValue(gameResult.getEmptyCell()));
        assertEquals(result.getViewName(), "algorithm");
    }

    @Test
    @DisplayName(value = "When call startGameWithAlgorithm method with incorrect parameter assert that returned ModelAndView will have \"algorithmStart\" view")
    void startGameWithAlgorithmFailByWrongParam() {
        ModelAndView result = controller.startGameWithAlgorithm("error");

        assertEquals(result.getViewName(), "algorithmStart");
    }

    @Test
    @DisplayName(value = "When game() method with session attribute \"game\" assert that in returned model will be attribute PlayerVsAlgorithm.class with key \"game\" and player and algorithm moves will be done")
    void game() {
        PlayerVsAlgorithmGame gameToTest = new PlayerVsAlgorithmGame();
        when(modelMock.getAttribute(sessionAttributeGameName)).thenReturn(gameToTest);

        ModelAndView result = controller.game(modelMock, 1);

        assertEquals("algorithm", result.getViewName());

        Object o = result.getModelMap().get(sessionAttributeGameName);
        assertTrue(o instanceof PlayerVsAlgorithmGame);
        PlayerVsAlgorithmGame gameResult = (PlayerVsAlgorithmGame) o;

        assertEquals(gameResult.getPlayer1(), gameResult.getCells().get(1));
        assertTrue(gameResult.getCells().containsValue(gameResult.getPlayer2()));
        assertTrue(gameResult.getCells().containsValue(gameResult.getEmptyCell()));

    }

    @Test
    @DisplayName(value = "When game() method with session attribute \"statistic\" assert that in returned model will be attribute Statistic.class with key \"statistic\"")
    void gameStatistic() {
        PlayerVsAlgorithmGame gameToTest = new PlayerVsAlgorithmGame();
        Statistic statisticToTest = new Statistic();
        when(modelMock.getAttribute(sessionAttributeGameName)).thenReturn(gameToTest);
        when(statisticServiceMock.getStatistic()).thenReturn(statisticToTest);

        ModelAndView result = controller.game(modelMock, 1);

        Object o = result.getModelMap().get(sessionAttributeStatisticName);
        assertTrue(o instanceof Statistic);
        Statistic statisticResult = (Statistic) o;

        assertEquals(statisticToTest, statisticResult);
    }

    @Test
    @DisplayName(value = "When game() method with finished game, assert that statistic will be updated")
    void gameStatisticUpdate() {
        PlayerVsAlgorithmGame gameToTest = new PlayerVsAlgorithmGame();
        gameToTest.getCells().put(1, gameToTest.getPlayer1());
        gameToTest.getCells().put(2, gameToTest.getPlayer1());
        gameToTest.getCells().put(3, gameToTest.getPlayer2());
        gameToTest.getCells().put(4, gameToTest.getPlayer2());
        gameToTest.getCells().put(5, gameToTest.getPlayer2());
        gameToTest.getCells().put(6, gameToTest.getPlayer1());
        gameToTest.getCells().put(7, gameToTest.getPlayer1());
        gameToTest.getCells().put(8, gameToTest.getPlayer2());
        gameToTest.winningCheckAndMakingMove(9);
        Statistic statisticToTest = new Statistic();
        when(modelMock.getAttribute(sessionAttributeGameName)).thenReturn(gameToTest);
        when(statisticServiceMock.getStatistic()).thenReturn(statisticToTest);

        controller.game(modelMock, 1);

        verify(statisticServiceMock, times(1)).updateStatistic(gameToTest);
    }

    @Test
    @DisplayName(value = "When game() method with no session attribute \"game\" assert that in returned modelAndView will have view \"algorithmStart\"")
    void gameFailWithNoSessionAttribute() {
        when(modelMock.getAttribute(sessionAttributeGameName)).thenReturn(null);
        ModelAndView modelAndViewToTest = controller.game(modelMock, 1);

        assertEquals("algorithmStart", modelAndViewToTest.getViewName());
    }
}