package com.daw.competitionGames.shared.infrastructure;

import com.daw.competitionGames.shared.domain.Game;
import com.daw.competitionGames.shared.domain.GameRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMovementRepository extends JpaRepository<Game, Long>, GameRepository {

}
