package com.avilapps.pdf_services.domain.repository;

import com.avilapps.pdf_services.domain.model.ContentFile;

public interface FileUploader {
    String upload(ContentFile contentFile);
}
