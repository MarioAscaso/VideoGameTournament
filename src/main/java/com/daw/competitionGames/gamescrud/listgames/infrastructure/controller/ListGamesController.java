package com.daw.competitionGames.gamescrud.listgames.infrastructure.controller;

import com.daw.competitionGames.gamescrud.listgames.application.ListGamesApp;
import com.daw.competitionGames.shared.domain.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 1. Usamos @RestController para devolver datos (JSON)
@RestController
// 2. Definimos una ruta base para la API
@RequestMapping("/api/games")
public class ListGamesController {

    private final ListGamesApp listGamesApp;

    public ListGamesController(ListGamesApp listGamesApp) {
        this.listGamesApp = listGamesApp;
    }

    @GetMapping
    public List<Game> listGames() {
        // Devuelve la lista de objetos, Spring Boot la convierte automáticamente a JSON
        return listGamesApp.execute();
    }
}