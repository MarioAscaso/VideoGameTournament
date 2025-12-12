package com.daw.competitionGames.gamescrud.updategame.domain.service;

import com.daw.competitionGames.gamescrud.updategame.application.UpdateGameRequest;
import com.daw.competitionGames.shared.domain.Game;
import com.daw.competitionGames.shared.domain.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateGameService {

    private final GameRepository gameRepository;

    public UpdateGameService(GameRepository gameRepository) {this.gameRepository = gameRepository;}

    public void execute(Long id, UpdateGameRequest request) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Juego no encontrado"));

        game.setName(request.getName());
        game.setDescription(request.getDescription());
        game.setAssociatedID(request.getAssociatedID());

        if (request.getBannerImage() != null && !request.getBannerImage().isEmpty()) {
            game.setBannerImage(request.getBannerImage());
        }
        if (request.getCardImage() != null && !request.getCardImage().isEmpty()) {
            game.setCardImage(request.getCardImage());
        }
        gameRepository.save(game);
    }
}