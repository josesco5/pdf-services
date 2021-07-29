package com.avilapps.pdf_services.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
public class FileUtils {

    public File getOutputDirectory() {
        File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
        File outputDirectory = new File(tempDirectory, "output");
        if (!outputDirectory.exists() && !outputDirectory.mkdir()) {
            throw new RuntimeException("Output Directory does not exists");
        }

        return outputDirectory;
    }

    public File receiveRemotePDF(MultipartFile multipartFile) {
        try {
            String filename = multipartFile.getOriginalFilename() != null ? multipartFile.getOriginalFilename() : "file.pdf";
            File destinationFile = new File(getOutputDirectory(), filename);
            multipartFile.transferTo(destinationFile);

            return destinationFile;
        } catch (Exception exception) {
            throw new RuntimeException("Error when transferring multipart file", exception);
        }
    }
}
