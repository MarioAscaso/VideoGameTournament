package com.daw.competitionGames.shared.storagefiles.infrastructure.controller;

import com.daw.competitionGames.shared.storagefiles.domain.interfaces.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    private final StorageService storageService;

    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file")MultipartFile aFile){
        storageService.store(aFile);
        return "redirect:/";
    }
}
