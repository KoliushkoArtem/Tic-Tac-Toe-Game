package com.neven.ticTacToeGame.utils;

import com.neven.ticTacToeGame.model.Game;
import com.neven.ticTacToeGame.model.PlayerVsAlgorithmGame;
import com.neven.ticTacToeGame.model.PlayerVsPlayerGame;
import com.neven.ticTacToeGame.model.PlayerVsRandomGame;

public class GamesFactory {

    public static Game getGame(GameTypes gameType) {
        switch (gameType){
            case PLAYER_VS_PLAYER:
                return new PlayerVsPlayerGame();
            case PLAYER_VS_RANDOM:
                return new PlayerVsRandomGame();
            case PLAYER_VS_ALGORITHM:
                return new PlayerVsAlgorithmGame();
        }
        return new PlayerVsPlayerGame();
    }
}
