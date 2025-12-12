package com.daw.competitionGames.gamescrud.listgames.infrastructure.controller;

import com.daw.competitionGames.gamescrud.listgames.application.ListGamesApp;
import com.daw.competitionGames.shared.domain.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class ListGamesController {

    private final ListGamesApp listGamesApp;

    public ListGamesController(ListGamesApp listGamesApp) {this.listGamesApp = listGamesApp;}

    @GetMapping
    public List<Game> listGames() {return listGamesApp.execute();}

    @GetMapping("/{id}")
    public Game getGame(@PathVariable Long id) {
        return listGamesApp.execute().stream()
                .filter(g -> g.getId() == id)
                .findFirst()
                .orElse(null);
    }
}