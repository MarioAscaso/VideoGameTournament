package com.daw.competitionGames.shared.domain;

import java.util.List;
import java.util.Optional;

public interface GameRepository {

    Game save(Game game);

    Optional<Game> findById(Long id);

    List<Game> findAll();

    List<Game> findAllByOrder();

    void delete(Game game);

}
