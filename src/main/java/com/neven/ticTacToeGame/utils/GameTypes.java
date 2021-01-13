package com.neven.ticTacToeGame.utils;

/**
 * {@link Enum} {@code GameTypes} care all types for the {@link com.neven.ticTacToeGame.model.Game} class.
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 */
public enum GameTypes {
    /**
     * Type of game for {@link com.neven.ticTacToeGame.model.PlayerVsPlayerGame}.
     */
    PLAYER_VS_PLAYER,
    /**
     * Type of game for {@link com.neven.ticTacToeGame.model.PlayerVsRandomGame}.
     */
    PLAYER_VS_RANDOM,
    /**
     * Type of game for {@link com.neven.ticTacToeGame.model.PlayerVsAlgorithmGame}.
     */
    PLAYER_VS_ALGORITHM
}