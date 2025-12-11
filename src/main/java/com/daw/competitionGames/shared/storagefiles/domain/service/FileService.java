package com.daw.competitionGames.shared.storagefiles.domain.service;

import com.daw.competitionGames.shared.storagefiles.core.config.StorageProperties;
import com.daw.competitionGames.shared.storagefiles.domain.interfaces.StorageService;
// IMPORT CORRECTO (Spring):
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService implements StorageService {

    private final Path rootLocation;

    public FileService(StorageProperties properties) {
        if(properties.getLocation().trim().length() == 0){
            this.rootLocation = Paths.get("upload-dir");
        } else {
            this.rootLocation = Paths.get(properties.getLocation());
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar el directorio de almacenamiento", e);
        }
    }

    @Override
    public boolean store(MultipartFile aFile) {
        try {
            if (aFile.isEmpty()) {
                System.err.println("Error: fichero vacío");
                return false;
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(aFile.getOriginalFilename()))
                    .normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                System.out.println("No se puede almacenar fuera del directorio actual.");
                return false;
            }
            try (InputStream inputStream = aFile.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                return true;
            }
        } catch (IOException e) {
            System.out.println("Fallo al almacenar el fichero.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            // Ahora 'Resource' se refiere a org.springframework.core.io.Resource,
            // que es compatible con UrlResource.
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se puede leer el fichero: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("No se puede leer el fichero: " + filename, e);
        }
    }
}