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

    @PostMapping("/game/new")
    public void newGame(@ModelAttribute NewGameRequest request,
                        @RequestParam("bannerFile") MultipartFile bannerFile,
                        @RequestParam("cardFile") MultipartFile cardFile) {
        if (!bannerFile.isEmpty()) {
            storageService.store(bannerFile);
            request.setBannerImage(bannerFile.getOriginalFilename());
        }
        if (!cardFile.isEmpty()) {
            storageService.store(cardFile);
            request.setCardImage(cardFile.getOriginalFilename());
        }
        newGameApp.execute(request);
    }
}