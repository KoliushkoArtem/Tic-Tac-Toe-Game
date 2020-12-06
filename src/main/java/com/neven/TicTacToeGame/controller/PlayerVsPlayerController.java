package com.neven.TicTacToeGame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "game/players")
public class PlayerVsPlayerController {

    @GetMapping
    @ResponseBody
    String getGameWithPlayers() {
        return "This page still in developing mode";
    }
}
