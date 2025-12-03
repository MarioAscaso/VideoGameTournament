package com.daw.competitionGames.shared.domain;

import jakarta.persistence.*;

@Entity
@Table(name="Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int numMaxPlayers;

    private String description;

    private Platform platform;

    public Game(String name, int numMaxPlayers, String description, Platform platform) {
        this.name = name;
        this.numMaxPlayers = numMaxPlayers;
        this.description = description;
        this.platform = platform;
    }

    public long getId() {return id;}
    public String getName() {return name;}
    public int getNumMaxPlayers() {return numMaxPlayers;}
    public String getDescription() {return description;}
    public Platform getPlatform() {return platform;}

    public void setName(String name) {this.name = name;}
    public void setNumMaxPlayers(int numMaxPlayers) {this.numMaxPlayers = numMaxPlayers;}
    public void setDescription(String description) {this.description = description;}
    public void setPlatform(Platform platform) {this.platform = platform;}
}
