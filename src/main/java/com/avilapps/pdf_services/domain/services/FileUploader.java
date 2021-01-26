package com.avilapps.pdf_services.domain.services;

import com.avilapps.pdf_services.domain.model.ContentFile;

public interface FileUploader {
    String upload(ContentFile contentFile);
}
