package com.avilapps.pdf_services.domain.model;

public class Document {
    private ContentFile originalFile;
    private ContentFile processedFile;
    private int initialFolio;

    public ContentFile getOriginalFile() {
        return originalFile;
    }

    public void setOriginalFile(ContentFile originalFile) {
        this.originalFile = originalFile;
    }

    public ContentFile getProcessedFile() {
        return processedFile;
    }

    public void setProcessedFile(ContentFile processedFile) {
        this.processedFile = processedFile;
    }

    public int getInitialFolio() {
        return initialFolio;
    }

    public void setInitialFolio(int initialFolio) {
        this.initialFolio = initialFolio;
    }
}
