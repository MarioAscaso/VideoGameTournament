package com.daw.competitionGames.shared.storagefiles.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "upload-dir";

    public String getLocation(){return location;}
    public void setLocation(){this.location = location;}
}
