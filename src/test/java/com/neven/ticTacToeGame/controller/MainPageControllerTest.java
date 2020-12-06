package com.neven.ticTacToeGame.controller;

import com.neven.ticTacToeGame.entity.Statistic;
import com.neven.ticTacToeGame.service.StatisticService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class MainPageControllerTest {

    private final StatisticService statisticServiceMock = mock(StatisticService.class);
    private final MainPageController mainPageController = new MainPageController(statisticServiceMock);

    @Test
    @DisplayName(value = "When call mainPage method assert that in statisticService method getStatistic() will be called 1 time, and received statistic will be set as model attribute")
    void mainPage() {
        Statistic statisticToTest = new Statistic();
        String statisticName = "statistic";
        when(statisticServiceMock.getStatistic()).thenReturn(statisticToTest);

        ModelAndView resultModelAndView = mainPageController.mainPage();
        ModelMap resultModelMap = resultModelAndView.getModelMap();

        verify(statisticServiceMock, times(1)).getStatistic();
        assertTrue(resultModelMap.containsKey(statisticName));
        assertEquals(statisticToTest, resultModelMap.get(statisticName));
    }
}