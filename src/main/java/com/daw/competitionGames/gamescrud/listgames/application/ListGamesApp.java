package com.daw.competitionGames.gamescrud.listgames.application;

import com.daw.competitionGames.gamescrud.listgames.domain.service.ListGamesService;
import com.daw.competitionGames.shared.domain.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListGamesApp {

    private final ListGamesService listGamesService;

    public ListGamesApp(ListGamesService listGamesService) {this.listGamesService = listGamesService;}

    public List<Game> execute(){return listGamesService.listAllGames();}
}
