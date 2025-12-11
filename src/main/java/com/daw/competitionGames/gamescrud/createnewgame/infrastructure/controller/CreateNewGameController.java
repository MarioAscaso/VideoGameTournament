package com.daw.competitionGames.gamescrud.createnewgame.infrastructure.controller;

import com.daw.competitionGames.gamescrud.createnewgame.application.CreateNewGameApp;
import com.daw.competitionGames.gamescrud.createnewgame.application.NewGameRequest;
import com.daw.competitionGames.shared.storagefiles.domain.interfaces.StorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CreateNewGameController {

    private final CreateNewGameApp newGameApp;
    private final StorageService storageService;

    public CreateNewGameController(CreateNewGameApp newGameApp, StorageService storageService) {
        this.newGameApp = newGameApp;
        this.storageService = storageService;
    }

    // Usamos void porque el cliente (JS) se encarga de la redirección si recibe un 200 OK
    @PostMapping("/game/new")
    public void newGame(@ModelAttribute NewGameRequest request,
                        @RequestParam("bannerFile") MultipartFile bannerFile,
                        @RequestParam("cardFile") MultipartFile cardFile) {

        // 1. Guardar archivos si existen
        if (!bannerFile.isEmpty()) {
            storageService.store(bannerFile);
            // Asignamos el nombre del archivo al DTO para guardarlo en BD
            request.setBannerImage(bannerFile.getOriginalFilename());
        }

        if (!cardFile.isEmpty()) {
            storageService.store(cardFile);
            request.setCardImage(cardFile.getOriginalFilename());
        }

        // 2. Ejecutar la lógica de negocio
        newGameApp.execute(request);
    }

    // ELIMINADO: showNewGameForm (ya no sirve en REST API con HTML estático)
}