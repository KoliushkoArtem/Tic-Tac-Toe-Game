package com.neven.TicTacToeGame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TicTacToeGameApplication.class)
class TicTacToeGameApplicationTest {

    @Test
    @DisplayName(value = "When application starts checking that context loads")
    public void contextLoads() {
    }
}