package com.avilapps.pdf_services.api.model;

import org.springframework.web.multipart.MultipartFile;

public class UploadDocumentRequest {

    private String destinationPath;
    private MultipartFile file;

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
