package com.neven.ticTacToeGame.controller;

import com.neven.ticTacToeGame.entity.Statistic;
import com.neven.ticTacToeGame.model.Game;
import com.neven.ticTacToeGame.model.PlayerVsAlgorithmGame;
import com.neven.ticTacToeGame.model.PlayerVsRandomGame;
import com.neven.ticTacToeGame.utils.GameTypes;
import com.neven.ticTacToeGame.utils.GamesFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class {@code PlayerVsRandomController} is a Spring Boot Controller class witch allow to serve HTTP requests from pages
 * with Player versus Random(randomly filled empty game fields) game.
 * <br>Path for requests "{pageContext}/game/random"
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 */
@Controller
@RequestMapping(value = "game/random")
@SessionAttributes("game")
public class PlayerVsRandomController {

    /**
     * Constant with a name of the view where user should make a decision who will start the game, user or algorithm.
     */
    private static final String GAME_START_PAGE_NAME = "randomStart";

    /**
     * Constant with a name of the player versus algorithm game view.
     */
    private static final String GAME_PAGE_NAME = "random";

    /**
     * Constant with a name of the model attribute for game.
     */
    private static final String GAME_MODEL_ATTRIBUTE_NAME = "game";

    /**
     * Constant with a String to verify does a user start the game.
     */
    private static final String USER_START_NAME = "user";

    /**
     * Constant with a String to verify does the Random start the game.
     */
    private static final String RANDOM_START_NAME = "random";

    /**
     * Allow to serve HTTP requests for requests "{pageContext}/game/random" by method GET.
     *
     * @return {@link ModelAndView} class with mane of the view where user should make a decision who will start the game, user or Random.
     */
    @GetMapping
    @ResponseBody
    ModelAndView getGameWithRandom() {
        return new ModelAndView(GAME_START_PAGE_NAME);
    }

    /**
     * Allow to serve HTTP requests for requests "{pageContext}/game/random/{whoStart}" by method GET.
     * Method will create the {@link PlayerVsRandomGame} and depends on who will start it will call {@link PlayerVsRandomGame#makeFirstMove()} method
     * if game will be started randomly. Created game will be added as model attribute to {@link ModelAndView} with a name
     * of player versus random game page view.
     * If {whoStart} parameter will be wrong, {@link ModelAndView} will be returned player versus random game start page name.
     *
     * @param whoStart is a String value with an information who will start the game, "user" or "random".
     * @return {@link ModelAndView} with name of Player versus Random game page view and {@link PlayerVsRandomGame} in model.
     */
    @GetMapping(value = "/{whoStart}")
    ModelAndView startGameWithRandom(@PathVariable(name = "whoStart") String whoStart) {
        Game newGame = GamesFactory.getGame(GameTypes.PLAYER_VS_RANDOM);
        if (whoStart.equals(USER_START_NAME)) {
            return new ModelAndView(GAME_PAGE_NAME, GAME_MODEL_ATTRIBUTE_NAME, newGame);
        } else if (whoStart.equals(RANDOM_START_NAME)) {
            newGame = newGame.makeFirstMove();
            return new ModelAndView(GAME_PAGE_NAME, GAME_MODEL_ATTRIBUTE_NAME, newGame);
        } else {
            return new ModelAndView(GAME_START_PAGE_NAME);
        }
    }

    /**
     * Allow to serve HTTP requests for requests "{pageContext}/game/random" by method POST.
     * Method will receive {@link PlayerVsRandomGame} from model with a number of the cell where user want to make a move.
     * Method {@link PlayerVsRandomGame#winningCheckAndMakingMove(int)} will be called with user move.
     * {@link PlayerVsRandomGame} with a user and random move will be returned in {@link ModelAndView} with a name
     * of player versus random game page view. If game wil be finished, returned {@link PlayerVsRandomGame} will care the result.
     *
     * @param model Spring framework interface which care {@link PlayerVsAlgorithmGame} ang {@link Statistic} as session attributes.
     * @param cell  Number of a cell on game field with acceptable values 1-9 where user want to make his move
     * @return {@link ModelAndView} with name of Player versus Random game page view and {@link PlayerVsRandomGame} in model.
     */
    @PostMapping
    ModelAndView game(Model model, @RequestParam(value = "cell") int cell) {
        Object game = model.getAttribute(GAME_MODEL_ATTRIBUTE_NAME);
        if (game instanceof PlayerVsRandomGame) {
            PlayerVsRandomGame playedGame = (PlayerVsRandomGame) game;
            Game gameResult = playedGame.winningCheckAndMakingMove(cell);
            return new ModelAndView(GAME_PAGE_NAME, GAME_MODEL_ATTRIBUTE_NAME, gameResult);
        }
        return new ModelAndView(GAME_START_PAGE_NAME);
    }
}