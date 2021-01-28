package com.avilapps.pdf_services.domain.services.impl;

import com.avilapps.pdf_services.common.exceptions.ServiceException;
import com.avilapps.pdf_services.domain.model.Document;
import com.avilapps.pdf_services.domain.services.DocumentService;
import com.avilapps.pdf_services.domain.repository.FileUploader;
import com.avilapps.pdf_services.domain.services.Foliator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Qualifier("documentFoliateService")
public class DocumentFoliateService implements DocumentService {

    private final Foliator foliator;
    private final FileUploader fileUploader;

    public DocumentFoliateService(Foliator foliator, FileUploader fileUploader) {
        this.foliator = foliator;
        this.fileUploader = fileUploader;
    }

    @Override
    public Document foliate(Document document) {
        try {
            File foliatedFile = foliator.foliate(document.getOriginalFile().getUrl(), document.getInitialFolio());
            document.getProcessedFile().setContent(foliatedFile);
            String foliatedFileUrl = fileUploader.upload(document.getProcessedFile());
            document.getProcessedFile().setUrl(foliatedFileUrl);

            return document;
        } catch (Exception exception) {
            throw new ServiceException(exception);
        }
    }
}
