package com.avilapps.pdf_services.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FoliationSettings {
    private final String fileUrl;
    private final int startFolio;

    public FoliationSettings(String fileUrl, int startFolio) {
        this.fileUrl = fileUrl;
        this.startFolio = startFolio;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public int getStartFolio() {
        return this.startFolio;
    }
}
