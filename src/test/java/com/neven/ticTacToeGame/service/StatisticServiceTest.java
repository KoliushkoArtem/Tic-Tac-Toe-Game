package com.neven.ticTacToeGame.service;

import com.neven.ticTacToeGame.entity.Statistic;
import com.neven.ticTacToeGame.model.Game;
import com.neven.ticTacToeGame.repository.StatisticRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatisticServiceTest {

    private final StatisticRepository statisticRepositoryMock = mock(StatisticRepository.class);
    private final StatisticService statisticService = new StatisticService(statisticRepositoryMock);
    private final Game gameMock = mock(Game.class);

    @Test
    @DisplayName(value = "When call getStatistic() method assert that in statisticRepository findByName() method will be called  1 time and founded statistic will be returned")
    void getStatisticWithExistStatistic() {
        Statistic testStatistic = new Statistic();
        testStatistic.setDraw(55);
        testStatistic.setAlgorithmWin(558);
        testStatistic.setPlayerWin(0);

        when(statisticRepositoryMock.findByName(Statistic.STATISTIC_NAME)).thenReturn(Optional.of(testStatistic));

        Statistic resultStatistic = statisticService.getStatistic();

        verify(statisticRepositoryMock, times(1)).findByName(Statistic.STATISTIC_NAME);
        assertEquals(testStatistic, resultStatistic);
    }

    @Test
    @DisplayName(value = "When call getStatistic() method assert that in statisticRepository findByName() method will return empty Optional, then new statistic will created, saved and returned")
    void getStatisticWithNotExistStatistic() {
        Statistic testStatistic = new Statistic();

        when(statisticRepositoryMock.findByName(Statistic.STATISTIC_NAME)).thenReturn(Optional.empty());
        when(statisticRepositoryMock.save(testStatistic)).thenReturn(testStatistic);

        Statistic resultStatistic = statisticService.getStatistic();

        verify(statisticRepositoryMock, times(1)).findByName(Statistic.STATISTIC_NAME);
        verify(statisticRepositoryMock, times(1)).save(testStatistic);
        assertEquals(testStatistic, resultStatistic);
    }

    @Test
    @DisplayName(value = "When call updateStatistic() method with player win, assert that player win in statistic will be increased by 1 and statistic will be saved")
    void updateStatisticPlayerWin() {
        int testStatisticValue = 25;
        Statistic testStatistic = new Statistic();
        testStatistic.setPlayerWin(testStatisticValue);
        when(statisticRepositoryMock.findByName(Statistic.STATISTIC_NAME)).thenReturn(Optional.of(testStatistic));
        when(gameMock.isPlayer1win()).thenReturn(true);

        statisticService.updateStatistic(gameMock);

        assertEquals(testStatisticValue + 1, testStatistic.getPlayerWin());
        verify(statisticRepositoryMock, times(1)).save(testStatistic);
    }

    @Test
    @DisplayName(value = "When call updateStatistic() method with algorithm win, assert that algorithm win in statistic will be increased by 1 and statistic will be saved")
    void updateStatisticAlgorithmWin() {
        int testStatisticValue = 25;
        Statistic testStatistic = new Statistic();
        testStatistic.setAlgorithmWin(testStatisticValue);
        when(statisticRepositoryMock.findByName(Statistic.STATISTIC_NAME)).thenReturn(Optional.of(testStatistic));
        when(gameMock.isPlayer2win()).thenReturn(true);

        statisticService.updateStatistic(gameMock);

        assertEquals(testStatisticValue + 1, testStatistic.getAlgorithmWin());
        verify(statisticRepositoryMock, times(1)).save(testStatistic);
    }

    @Test
    @DisplayName(value = "When call updateStatistic() method with draw, assert that draw in statistic will be increased by 1 and statistic will be saved")
    void updateStatisticDraw() {
        int testStatisticValue = 25;
        Statistic testStatistic = new Statistic();
        testStatistic.setDraw(testStatisticValue);
        when(statisticRepositoryMock.findByName(Statistic.STATISTIC_NAME)).thenReturn(Optional.of(testStatistic));
        when(gameMock.isDraw()).thenReturn(true);

        statisticService.updateStatistic(gameMock);

        assertEquals(testStatisticValue + 1, testStatistic.getDraw());
        verify(statisticRepositoryMock, times(1)).save(testStatistic);
    }
}