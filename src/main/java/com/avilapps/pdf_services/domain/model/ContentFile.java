package com.avilapps.pdf_services.domain.model;

import java.io.File;

public class ContentFile {
    private String url;
    private File content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public File getContent() {
        return content;
    }

    public void setContent(File content) {
        this.content = content;
    }
}
