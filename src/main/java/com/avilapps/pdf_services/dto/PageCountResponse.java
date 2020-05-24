package com.avilapps.pdf_services.dto;

public class PageCountResponse {
    private final String fileUrl;
    private final int pagesTotal;

    public PageCountResponse(String fileUrl, int pagesTotal) {
        this.fileUrl = fileUrl;
        this.pagesTotal = pagesTotal;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public int getPagesTotal() {
        return pagesTotal;
    }
}
