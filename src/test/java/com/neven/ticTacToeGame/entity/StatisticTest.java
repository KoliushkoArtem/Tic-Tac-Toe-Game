package com.neven.ticTacToeGame.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticTest {

    @Test
    @DisplayName(value = "When call getTotalGames() method, assert that returned value will be equals to summ of playerWin and algorithmWin wins and draws")
    void getTotalGames() {
        Statistic testStatistic = new Statistic();
        testStatistic.setPlayerWin(0);
        testStatistic.setAlgorithmWin(155);
        testStatistic.setDraw(325);

        assertEquals(480, testStatistic.getTotalGames());
    }
}