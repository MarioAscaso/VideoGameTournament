package com.daw.competitionGames.gamescrud.updategame.application;

import com.daw.competitionGames.shared.domain.AssociatedID;

public class UpdateGameRequest {
    private String name;
    private String description;
    private AssociatedID associatedID;
    private String bannerImage;
    private String cardImage;

    public UpdateGameRequest() {}

    public UpdateGameRequest(String name, String description, AssociatedID associatedID, String bannerImage, String cardImage) {
        this.name = name;
        this.description = description;
        this.associatedID = associatedID;
        this.bannerImage = bannerImage;
        this.cardImage = cardImage;
    }

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