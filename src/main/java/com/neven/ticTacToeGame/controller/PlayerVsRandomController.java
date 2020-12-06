package com.neven.ticTacToeGame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "game/random")
public class PlayerVsRandomController {

    @GetMapping
    @ResponseBody
    String getGameWithRandom() {
        return "This page still in developing mode";
    }
}
