package com.avilapps.pdf_services.services;

import com.avilapps.pdf_services.dto.UploadSettings;

import java.io.File;
import java.net.URL;

public interface FileUploader {
    URL upload(File pdf, UploadSettings settings);
}
