package com.neven.ticTacToeGame.controller;

import com.neven.ticTacToeGame.model.Game;
import com.neven.ticTacToeGame.model.PlayerVsPlayerGame;
import com.neven.ticTacToeGame.utils.GameTypes;
import com.neven.ticTacToeGame.utils.GamesFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class {@code PlayerVsPlayerController} is a Spring Boot Controller class witch allow to serve HTTP requests
 * from pages with Player versus Player game.
 * <br>Path for requests "{pageContext}/game/players"
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 */
@Controller
@RequestMapping(value = "game/players")
@SessionAttributes("game")
public class PlayerVsPlayerController {

    /**
     * Constant with a name of player versus algorithm game view.
     */
    private static final String GAME_PAGE_NAME = "players";

    /**
     * Constant with a name of the model attribute for game.
     */
    private static final String GAME_MODEL_ATTRIBUTE_NAME = "game";

    /**
     * Allow to serve HTTP requests for requests "{pageContext}/game/players" by method GET.
     *
     * @return {@link ModelAndView} class with mane of the view of the Player versus Player game page
     * with new {@link PlayerVsPlayerGame} as a {@link Model} attribute.
     */
    @GetMapping
    ModelAndView startGameWithPlayers() {
        return new ModelAndView(GAME_PAGE_NAME, GAME_MODEL_ATTRIBUTE_NAME, GamesFactory.getGame(GameTypes.PLAYER_VS_PLAYER));
    }

    /**
     * Allow to serve HTTP requests for requests "{pageContext}/game/players" by method POST.
     * Method will receive {@link PlayerVsPlayerGame} from model with number of cell where user want to make a move.
     * Method {@link PlayerVsPlayerGame#winningCheckAndMakingMove(int)} will be called with user move. Current user will be
     * changed and {@link PlayerVsPlayerGame} will be returned as {@link Model} attribute to Player versus Player game page view.
     *
     * @param model Spring framework interface which care {@link PlayerVsPlayerGame} as session attribute.
     * @param cell  Number of cell on game field with acceptable values 1-9 where user want to make his move
     * @return {@link ModelAndView} with name of Player versus Player game page view and {@link PlayerVsPlayerGame} in model.
     */
    @PostMapping
    ModelAndView game(Model model, @RequestParam(value = "cell") int cell) {
        Object game = model.getAttribute(GAME_MODEL_ATTRIBUTE_NAME);
        if (game instanceof PlayerVsPlayerGame) {
            Game playerVsPlayerGame = ((PlayerVsPlayerGame) game).winningCheckAndMakingMove(cell);
            return new ModelAndView(GAME_PAGE_NAME, GAME_MODEL_ATTRIBUTE_NAME, playerVsPlayerGame);
        } else {
            return new ModelAndView(GAME_PAGE_NAME, GAME_MODEL_ATTRIBUTE_NAME, GamesFactory.getGame(GameTypes.PLAYER_VS_PLAYER));
        }
    }
}