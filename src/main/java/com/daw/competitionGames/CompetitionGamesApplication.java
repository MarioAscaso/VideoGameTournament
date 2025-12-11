package com.daw.competitionGames;

import com.daw.competitionGames.shared.storagefiles.domain.interfaces.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CompetitionGamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetitionGamesApplication.class, args);
	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}
}
