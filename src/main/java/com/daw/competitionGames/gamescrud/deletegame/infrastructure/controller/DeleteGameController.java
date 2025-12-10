package com.daw.competitionGames.gamescrud.deletegame.infrastructure.controller;

import com.daw.competitionGames.gamescrud.deletegame.application.DeleteGameApp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class DeleteGameController {

    private final DeleteGameApp deleteGameApp;

    public DeleteGameController(DeleteGameApp deleteGameApp) {
        this.deleteGameApp = deleteGameApp;
    }

    @PostMapping("/games/delete")
    public String deleteGame(@RequestParam Long id){
        deleteGameApp.execute(id);
        return "redirect:/";
    }

}
