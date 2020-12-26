package com.neven.ticTacToeGame.controller;

import com.neven.ticTacToeGame.model.Game;
import com.neven.ticTacToeGame.model.PlayerVsPlayerGame;
import com.neven.ticTacToeGame.utils.GameTypes;
import com.neven.ticTacToeGame.utils.GamesFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "game/players")
@SessionAttributes("game")
public class PlayerVsPlayerController {

    @GetMapping
    ModelAndView startGameWithPlayers() {
        return new ModelAndView("players", "game", GamesFactory.getGame(GameTypes.PLAYER_VS_PLAYER));
    }

    @PostMapping
    ModelAndView game(Model model, @RequestParam(value = "cell") int cell) {
        Object game = model.getAttribute("game");
        if (game instanceof PlayerVsPlayerGame) {
            Game playerVsPlayerGame = ((PlayerVsPlayerGame) game).winningCheckAndMakingMove(cell);
            return new ModelAndView("players", "game", playerVsPlayerGame);
        } else {
            return new ModelAndView("players", "game", GamesFactory.getGame(GameTypes.PLAYER_VS_PLAYER));
        }
    }
}
