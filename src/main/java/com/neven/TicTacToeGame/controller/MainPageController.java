package com.neven.TicTacToeGame.controller;

import com.neven.TicTacToeGame.entity.Statistic;
import com.neven.TicTacToeGame.service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class MainPageController {

    private final StatisticService statisticService;

    public MainPageController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    ModelAndView mainPage() {
        Statistic statistic = statisticService.getStatistic();
        return new ModelAndView("main", "statistic", statistic);
    }
}
