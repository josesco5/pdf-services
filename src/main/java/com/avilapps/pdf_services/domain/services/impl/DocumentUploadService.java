package com.avilapps.pdf_services.domain.services.impl;

import com.avilapps.pdf_services.common.exceptions.RepositoryException;
import com.avilapps.pdf_services.common.exceptions.ServiceException;
import com.avilapps.pdf_services.domain.model.ContentFile;
import com.avilapps.pdf_services.domain.repository.FileRepository;
import com.avilapps.pdf_services.domain.services.DocumentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("documentUploadService")
public class DocumentUploadService implements DocumentService {

    private final FileRepository fileRepository;

    public DocumentUploadService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public String upload(ContentFile contentFile) {
        try {
            return fileRepository.upload(contentFile);
        } catch (RepositoryException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new ServiceException(exception);
        }
    }
}
