package com.avilapps.pdf_services.domain.services.impl;

import com.avilapps.pdf_services.common.exceptions.ServiceException;
import com.avilapps.pdf_services.domain.model.Document;
import com.avilapps.pdf_services.domain.services.DocumentService;
import com.avilapps.pdf_services.domain.repository.FileRepository;
import com.avilapps.pdf_services.domain.services.Foliator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Qualifier("documentFoliateService")
public class DocumentFoliateService implements DocumentService {

    private final Foliator foliator;
    private final FileRepository fileRepository;

    public DocumentFoliateService(Foliator foliator, FileRepository fileRepository) {
        this.foliator = foliator;
        this.fileRepository = fileRepository;
    }

    @Override
    public Document foliate(Document document) {
        try {
            File foliatedFile = foliator.foliate(document.getOriginalFile().getUrl(), document.getInitialFolio());
            document.getProcessedFile().setContent(foliatedFile);
            String foliatedFileUrl = fileRepository.upload(document.getProcessedFile());
            document.getProcessedFile().setUrl(foliatedFileUrl);

            return document;
        } catch (Exception exception) {
            throw new ServiceException(exception);
        }
    }
}
