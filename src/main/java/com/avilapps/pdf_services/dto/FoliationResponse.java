package com.avilapps.pdf_services.dto;

public class FoliationResponse {
    private final String fileUrl;
    private final String resultFileUrl;

    public FoliationResponse(String fileUrl, String resultFileUrl) {
        this.fileUrl = fileUrl;
        this.resultFileUrl = resultFileUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getResultFileUrl() {
        return resultFileUrl;
    }
}
