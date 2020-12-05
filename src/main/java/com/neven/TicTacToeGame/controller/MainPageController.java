package com.neven.TicTacToeGame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class MainPageController {

    @GetMapping
    ModelAndView mainPage() {
        return new ModelAndView("main");
    }
}
