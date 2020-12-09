package com.neven.ticTacToeGame.controller;

import com.neven.ticTacToeGame.model.Game;
import com.neven.ticTacToeGame.model.PlayerVsRandomGame;
import com.neven.ticTacToeGame.utils.GameTypes;
import com.neven.ticTacToeGame.utils.GamesFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "game/random")
@SessionAttributes("game")
public class PlayerVsRandomController {

    @GetMapping
    @ResponseBody
    ModelAndView getGameWithRandom() {
        return new ModelAndView("randomStart");
    }

    @GetMapping(value = "/{whoStart}")
    ModelAndView startGameWithRandom(@PathVariable(name = "whoStart") String whoStart) {
        Game newGame = GamesFactory.getGame(GameTypes.PLAYER_VS_RANDOM);
        if (whoStart.equals("user")) {
            return new ModelAndView("random", "game", newGame);
        } else if (whoStart.equals("random")) {
            newGame = newGame.makeFirstMove();
            return new ModelAndView("random", "game", newGame);
        } else {
            return new ModelAndView("randomStart");
        }
    }

    @PostMapping
    ModelAndView game(Model model, @RequestParam(value = "cell") int cell) {
        Object game = model.getAttribute("game");
        if (game instanceof PlayerVsRandomGame) {
            PlayerVsRandomGame playedGame = (PlayerVsRandomGame) game;
            Game gameResult = playedGame.winningCheckAndMakingMove(cell);
            return new ModelAndView("random", "game", gameResult);
        }
        return new ModelAndView("randomStart");
    }
}
