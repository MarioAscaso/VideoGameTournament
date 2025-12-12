package com.daw.competitionGames.shared.domain;

import jakarta.persistence.*;

@Entity
@Table(name="game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private AssociatedID associatedID;

    private String bannerImage;

    private String cardImage;

    public Game(){}

    public Game(String name, String description, AssociatedID associatedID, String bannerImage, String cardImage) {
        this.name = name;
        this.description = description;
        this.associatedID = associatedID;
        this.bannerImage = bannerImage;
        this.cardImage = cardImage;
    }

    public long getId() {return id;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public AssociatedID getAssociatedID() {return associatedID;}
    public String getBannerImage() {return bannerImage;}
    public String getCardImage() {return cardImage;}

    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setAssociatedID(AssociatedID associatedID) {this.associatedID = associatedID;}
    public void setBannerImage(String bannerImage) {this.bannerImage = bannerImage;}
    public void setCardImage(String cardImage) {this.cardImage = cardImage;}
}
