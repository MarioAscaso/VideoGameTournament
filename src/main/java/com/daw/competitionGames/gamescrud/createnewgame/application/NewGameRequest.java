package com.daw.competitionGames.gamescrud.createnewgame.application;

import com.daw.competitionGames.shared.domain.Platform;

public class NewGameRequest {

    private String name;
    private int numMaxPlayers;
    private String description;
    private Platform platform;

    public NewGameRequest(){}

    public NewGameRequest(String name, int numMaxPlayers, String description, Platform platform) {
        this.name = name;
        this.numMaxPlayers = numMaxPlayers;
        this.description = description;
        this.platform = platform;
    }

    public String getName() {return name;}
    public int getNumMaxPlayers() {return numMaxPlayers;}
    public String getDescription() {return description;}
    public Platform getPlatform() {return platform;}

    public void setName(String name) {this.name = name;}
    public void setNumMaxPlayers(int numMaxPlayers) {this.numMaxPlayers = numMaxPlayers;}
    public void setDescription(String description) {this.description = description;}
    public void setPlatform(Platform platform) {this.platform = platform;}
}
