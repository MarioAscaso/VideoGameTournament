package com.daw.competitionGames.gamescrud.deletegame.application;

import com.daw.competitionGames.gamescrud.deletegame.domain.service.DeleteGameService;
import org.springframework.stereotype.Service;

@Service
public class DeleteGameApp {

    private final DeleteGameService deleteGameService;

    public DeleteGameApp(DeleteGameService deleteGameService) {
        this.deleteGameService = deleteGameService;
    }

    public void execute(Long id){deleteGameService.execute(id);}
}
