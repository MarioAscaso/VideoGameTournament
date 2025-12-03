package com.daw.competitionGames.gamescrud.listgames.infrastructure.controller;

import com.daw.competitionGames.shared.domain.Game;
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
    public List<Game> listGames(Model model){
        return listGamesApp.execute();
    }

}
