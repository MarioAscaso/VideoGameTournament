package com.daw.competitionGames.shared.storagefiles.domain.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init();
    boolean store(MultipartFile aFile);
    Resource loadAsResource(String filename);
}
