package com.daw.competitionGames.shared.storagefiles.domain.service;

import com.daw.competitionGames.shared.storagefiles.core.config.StorageProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    private final Path rootLocation;

    public FileService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            System.out.println("No pudimos inicializar el Fyle System");
        }
    }

    public boolean store(MultipartFile aFile) {
        try {
            if (aFile.isEmpty()) {
                System.err.println("Error al almacenar el File System. (fichero vacío)");
                return false;
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(aFile.getOriginalFilename())).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                System.out.println("No pudimos almacenar el File System. (fuera de directorio)");
                return false;
            }
            try (InputStream inputStream = aFile.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                return true;
            }
        } catch (IOException e) {
            System.out.println("No pudimos almacenar el File System.");
            return false;
        }
    }

}
