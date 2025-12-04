package com.daw.competitionGames.gamescrud.listgames.infrastructure.controller;

import org.springframework.ui.Model;
import com.daw.competitionGames.gamescrud.listgames.application.ListGamesApp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ListGamesController {

    private final ListGamesApp listGamesApp;

    public ListGamesController(ListGamesApp listGamesApp) {this.listGamesApp = listGamesApp;}

    @GetMapping("/")
    public String listGames(Model model){
        model.addAttribute("games", listGamesApp.execute());
        return "index";
    }

}
