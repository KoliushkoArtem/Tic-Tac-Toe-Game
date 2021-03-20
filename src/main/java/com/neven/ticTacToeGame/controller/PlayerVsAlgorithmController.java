package com.neven.ticTacToeGame.controller;

import com.neven.ticTacToeGame.entity.Statistic;
import com.neven.ticTacToeGame.model.Game;
import com.neven.ticTacToeGame.model.PlayerVsAlgorithmGame;
import com.neven.ticTacToeGame.service.StatisticService;
import com.neven.ticTacToeGame.utils.GameTypes;
import com.neven.ticTacToeGame.utils.GamesFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class {@code PlayerVsAlgorithmController} is a Spring Boot Controller class witch allow to serve HTTP requests
 * from pages with Player versus Algorithm game.
 * Class has injected statistic service({@link StatisticService}) for showing statistic for games with an algorithm(MiniMax) on game page.
 * <br>Path for requests "{pageContext}/game/algorithm"
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 */
@Controller
@RequestMapping(value = "/game/algorithm")
@SessionAttributes({"game", "statistic"})
public class PlayerVsAlgorithmController {

    /**
     * Constant with a name of the view where user should make a decision who will start the game, user or algorithm.
     */
    private static final String GAME_START_PAGE_NAME = "algorithmStart";

    /**
     * Constant with a name of the player versus algorithm game view.
     */
    private static final String GAME_PAGE_NAME = "algorithm";

    /**
     * Constant with a name of the model attribute for game.
     */
    private static final String GAME_MODEL_ATTRIBUTE_NAME = "game";

    /**
     * Constant with a name of the model attribute for statistic.
     */
    private static final String STATISTIC_MODEL_ATTRIBUTE_NAME = "statistic";

    /**
     * Constant with a String to verify does a user start the game.
     */
    private static final String USER_START_NAME = "user";

    /**
     * Constant with a String to verify does the algorithm start the game.
     */
    private static final String ALGORITHM_START_NAME = "algorithm";

    /**
     * This is an injected statistic service({@link StatisticService}) witch allow to get(from DB) and update(save to DB) statistic for games with an algorithm(MiniMax).
     */
    private final StatisticService statisticService;

    /**
     * Create class with an injection of statistic service for games with an algorithm(MiniMax).
     *
     * @param statisticService Injection of {@link StatisticService} class
     */
    public PlayerVsAlgorithmController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    /**
     * Allow to serve HTTP requests for requests "{pageContext}/game/algorithm" by method GET.
     *
     * @return {@link ModelAndView} class with mane of the view where user should make a decision who will start the game, user or algorithm.
     */
    @GetMapping
    @ResponseBody
    public ModelAndView getGameWithAlgorithm() {
        return new ModelAndView(GAME_START_PAGE_NAME);
    }

    /**
     * Allow to serve HTTP requests for requests "{pageContext}/game/algorithm/{whoStart}" by method GET.
     * Method will create the {@link PlayerVsAlgorithmGame} and depends on who will start it will call {@link PlayerVsAlgorithmGame#makeFirstMove()} method
     * if game will be started by algorithm. Created game will be added as model attribute to {@link ModelAndView} with a name
     * of player versus algorithm game page view.
     * If {whoStart} parameter will be wrong, {@link ModelAndView} will return player versus algorithm game start page name.
     *
     * @param whoStart is a String value with an information who will start the game, "user" or "algorithm".
     * @return {@link ModelAndView} with name of player versus algorithm game page view and {@link PlayerVsAlgorithmGame} in model.
     */
    @GetMapping(value = "/{whoStart}")
    public ModelAndView startGameWithAlgorithm(@PathVariable(name = "whoStart") String whoStart) {
        Game newGame = GamesFactory.getGame(GameTypes.PLAYER_VS_ALGORITHM);
        if (whoStart.equals(USER_START_NAME)) {
            return new ModelAndView(GAME_PAGE_NAME, GAME_MODEL_ATTRIBUTE_NAME, newGame);
        } else if (whoStart.equals(ALGORITHM_START_NAME)) {
            newGame = newGame.makeFirstMove();
            return new ModelAndView(GAME_PAGE_NAME, GAME_MODEL_ATTRIBUTE_NAME, newGame);
        } else {
            return new ModelAndView(GAME_START_PAGE_NAME);
        }
    }

    /**
     * Allow to serve HTTP requests for requests "{pageContext}/game/algorithm" by method POST.
     * Method receive {@link PlayerVsAlgorithmGame} from model with a number of the cell where user want to make a move.
     * Method {@link PlayerVsAlgorithmGame#winningCheckAndMakingMove(int)} will be called with user move.
     * {@link PlayerVsAlgorithmGame} with a {@link Statistic} will be returned in {@link ModelAndView} with name of
     * player versus algorithm game page view.
     * If game will be finished, game {@link Statistic} will be updated and returned {@link PlayerVsAlgorithmGame} will care the result.
     *
     * @param model Spring framework interface which care {@link PlayerVsAlgorithmGame} ang {@link Statistic} as session attributes.
     * @param cell  Number of cell on game field with acceptable values 1-9 where user want to make his move
     * @return {@link ModelAndView} with name of player versus algorithm game page view and {@link PlayerVsAlgorithmGame} in model.
     */
    @PostMapping
    public ModelAndView game(Model model, @RequestParam(value = "cell") int cell) {
        Object game = model.getAttribute(GAME_MODEL_ATTRIBUTE_NAME);
        if (game instanceof PlayerVsAlgorithmGame) {
            PlayerVsAlgorithmGame playedGame = (PlayerVsAlgorithmGame) game;
            Game gameResult = playedGame.winningCheckAndMakingMove(cell);
            if (gameResult.isPlayer1win() || gameResult.isPlayer2win() || gameResult.isDraw()) {
                statisticService.updateStatistic(gameResult);
            }
            Statistic statistic = statisticService.getStatistic();
            ModelAndView modelAndViewToReturn = new ModelAndView(GAME_PAGE_NAME);
            modelAndViewToReturn.addObject(GAME_MODEL_ATTRIBUTE_NAME, gameResult);
            modelAndViewToReturn.addObject(STATISTIC_MODEL_ATTRIBUTE_NAME, statistic);
            return modelAndViewToReturn;
        }
        return new ModelAndView(GAME_START_PAGE_NAME);
    }
}