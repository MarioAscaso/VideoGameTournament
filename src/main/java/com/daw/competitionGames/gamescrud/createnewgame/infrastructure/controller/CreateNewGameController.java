package com.daw.competitionGames.gamescrud.createnewgame.infrastructure.controller;

import com.daw.competitionGames.gamescrud.createnewgame.application.CreateNewGameApp;
import com.daw.competitionGames.gamescrud.createnewgame.application.NewGameRequest;
import com.daw.competitionGames.shared.storagefiles.domain.interfaces.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CreateNewGameController {

    private final CreateNewGameApp newGameApp;
    private final StorageService storageService; // Añadido servicio de almacenamiento

    // Inyectamos también el StorageService en el constructor
    public CreateNewGameController(CreateNewGameApp newGameApp, StorageService storageService) {
        this.newGameApp = newGameApp;
        this.storageService = storageService;
    }

    @PostMapping("/game/new")
    public String newGame(@ModelAttribute NewGameRequest request,
                          @RequestParam("bannerFile") MultipartFile bannerFile, // Recibimos el fichero del banner
                          @RequestParam("cardFile") MultipartFile cardFile) {   // Recibimos el fichero de la carta

        // 1. Gestionar imagen del Banner
        if (!bannerFile.isEmpty()) {
            storageService.store(bannerFile); // Guardamos el fichero físico
            request.setBannerImage(bannerFile.getOriginalFilename()); // Guardamos el nombre en la BD
        }

        // 2. Gestionar imagen de la Carta
        if (!cardFile.isEmpty()) {
            storageService.store(cardFile);
            request.setCardImage(cardFile.getOriginalFilename());
        }

        // 3. Crear el juego
        newGameApp.execute(request);
        return "redirect:/";
    }

    @GetMapping("/new-game-form")
    public String showNewGameForm(Model model) {
        return "newGame";
    }
}