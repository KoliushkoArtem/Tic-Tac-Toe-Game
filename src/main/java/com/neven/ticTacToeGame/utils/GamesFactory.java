package com.neven.ticTacToeGame.utils;

import com.neven.ticTacToeGame.model.Game;
import com.neven.ticTacToeGame.model.PlayerVsAlgorithmGame;
import com.neven.ticTacToeGame.model.PlayerVsPlayerGame;
import com.neven.ticTacToeGame.model.PlayerVsRandomGame;

/**
 * Class {@code GamesFactory} is a <u>Factory Design Pattern</u>.
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 * @see <a href="https://www.journaldev.com/1392/factory-design-pattern-in-java">Factory Design Pattern</a>
 */
public class GamesFactory {

    /**
     * This method return specific {@link Game} (can be {@link PlayerVsPlayerGame}, {@link PlayerVsRandomGame},
     * {@link PlayerVsAlgorithmGame}) by receiving game type from {@link GameTypes}.
     *
     * @param gameType {@link Enum} {@link GameTypes} class, which contain different types of the {@link Game}.
     * @return {@link Game} with a specific game type, can be {@link PlayerVsPlayerGame}, {@link PlayerVsRandomGame}
     * or {@link PlayerVsAlgorithmGame}.
     */
    public static Game getGame(GameTypes gameType) {
        switch (gameType) {
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