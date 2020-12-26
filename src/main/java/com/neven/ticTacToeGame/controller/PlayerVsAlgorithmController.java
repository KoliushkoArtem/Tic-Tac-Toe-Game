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

@Controller
@RequestMapping(value = "/game/algorithm")
@SessionAttributes({"game", "statistic"})
public class PlayerVsAlgorithmController {

    private final StatisticService statisticService;

    public PlayerVsAlgorithmController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    @ResponseBody
    ModelAndView getGameWithAlgorithm() {
        return new ModelAndView("algorithmStart");
    }

    @GetMapping(value = "/{whoStart}")
    ModelAndView startGameWithAlgorithm(@PathVariable(name = "whoStart") String whoStart) {
        Game newGame = GamesFactory.getGame(GameTypes.PLAYER_VS_ALGORITHM);
        if (whoStart.equals("user")) {
            return new ModelAndView("algorithm", "game", newGame);
        } else if (whoStart.equals("algorithm")) {
            newGame = newGame.makeFirstMove();
            return new ModelAndView("algorithm", "game", newGame);
        } else {
            return new ModelAndView("algorithmStart");
        }
    }

    @PostMapping
    ModelAndView game(Model model, @RequestParam(value = "cell") int cell) {
        Object game = model.getAttribute("game");
        if (game instanceof PlayerVsAlgorithmGame) {
            PlayerVsAlgorithmGame playedGame = (PlayerVsAlgorithmGame) game;
            Game gameResult = playedGame.winningCheckAndMakingMove(cell);
            if (gameResult.isPlayer1win() || gameResult.isPlayer2win() || gameResult.isDraw()) {
                statisticService.updateStatistic(gameResult);
            }
            Statistic statistic = statisticService.getStatistic();
            ModelAndView modelAndViewToReturn = new ModelAndView("algorithm");
            modelAndViewToReturn.addObject("game", gameResult);
            modelAndViewToReturn.addObject("statistic", statistic);
            return modelAndViewToReturn;
        }
        return new ModelAndView("algorithmStart");
    }
}