package com.neven.TicTacToeGame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/game/algorithm")
public class PlayerVsAlgorithmController {
    @GetMapping
    @ResponseBody
    String getGameWithAlgorithm() {
        return "This page still in developing mode";
    }

}
