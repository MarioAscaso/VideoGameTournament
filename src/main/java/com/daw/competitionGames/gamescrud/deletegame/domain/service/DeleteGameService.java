package com.daw.competitionGames.gamescrud.deletegame.domain.service;

import com.daw.competitionGames.shared.domain.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteGameService {

    private final GameRepository gameRepository;

    public DeleteGameService(GameRepository gameRepository) {this.gameRepository = gameRepository;}

    public void execute(Long id){gameRepository.deleteById(id);}
}
