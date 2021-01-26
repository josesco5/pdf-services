package com.avilapps.pdf_services.api.model;

public class FoliateDocumentRequest {
    private String originPath;
    private String initialFolio;
    private String destinationPath;

    public String getOriginPath() {
        return originPath;
    }

    public void setOriginPath(String originPath) {
        this.originPath = originPath;
    }

    public String getInitialFolio() {
        return initialFolio;
    }

    public void setInitialFolio(String initialFolio) {
        this.initialFolio = initialFolio;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }
}
