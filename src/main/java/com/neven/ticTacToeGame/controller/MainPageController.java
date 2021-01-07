package com.neven.ticTacToeGame.controller;

import com.neven.ticTacToeGame.entity.Statistic;
import com.neven.ticTacToeGame.service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class {@code MainPageController} is a Spring Boot Controller class witch allow to serve HTTP requests
 * from main page of application.
 * Class has injected statistic service({@link StatisticService}) for showing statistic for games with an algorithm(MiniMax) on main page.
 * <br/>Path for requests "{pageContext}/"
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 */
@Controller
@RequestMapping(value = "/")
@SessionAttributes("statistic")
public class MainPageController {

    /**
     * Constant with model attribute name for statistic.
     */
    private static final String STATISTIC_MODEL_NAME = "statistic";

    /**
     * Constant with a name of main page view.
     */
    private static final String MAIN_PAGE_NAME = "main";

    /**
     * This is an injected statistic service({@link StatisticService}) witch allow to get statistic for games with an algorithm(MiniMax) from DB.
     */
    private final StatisticService statisticService;

    /**
     * Create class with an injection of statistic service({@link StatisticService}) to show results of the games
     * with an algorithm(MiniMax) on main page.
     *
     * @param statisticService Injection of {@link StatisticService} class.
     */
    public MainPageController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    /**
     * Allow to serve HTTP requests for requests "{pageContext}/" by method GET.
     *
     * @return {@link ModelAndView} with mane of the main page view and {@link Statistic} of the games with an algorithm(MiniMax) in model.
     */
    @GetMapping
    ModelAndView mainPage() {
        Statistic statistic = statisticService.getStatistic();
        return new ModelAndView(MAIN_PAGE_NAME, STATISTIC_MODEL_NAME, statistic);
    }
}