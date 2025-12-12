package com.daw.competitionGames.gamescrud.createnewgame.domain.service;

import com.daw.competitionGames.gamescrud.createnewgame.application.NewGameRequest;
import com.daw.competitionGames.shared.domain.Game;
import com.daw.competitionGames.shared.domain.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateNewGameService {

    private final GameRepository gameRepository;

    public CreateNewGameService(GameRepository gameRepository) {this.gameRepository = gameRepository;}

    public void execute(NewGameRequest request) {
        Game game = new Game(
                request.getName(),
                request.getDescription(),
                request.getAssociatedID(),
                request.getBannerImage(),
                request.getCardImage()
        );
        gameRepository.save(game);
    }

}
