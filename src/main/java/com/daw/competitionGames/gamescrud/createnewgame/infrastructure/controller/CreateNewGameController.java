package com.daw.competitionGames.gamescrud.createnewgame.infrastructure.controller;

import org.springframework.ui.Model;
import com.daw.competitionGames.gamescrud.createnewgame.application.CreateNewGameApp;
import com.daw.competitionGames.gamescrud.createnewgame.application.NewGameRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateNewGameController {

    private final CreateNewGameApp newGameApp;

    public CreateNewGameController(CreateNewGameApp newGameApp) {
        this.newGameApp = newGameApp;
    }

    @PostMapping("/game/new")
    public String newGame(@ModelAttribute NewGameRequest request){
        newGameApp.execute(request);
        return "redirect:/";
    }

    @GetMapping("/new-game-form")
    public String showNewGameForm(Model model) {
        return "newGame";
    }

}
