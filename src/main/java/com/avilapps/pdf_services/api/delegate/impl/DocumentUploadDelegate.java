package com.avilapps.pdf_services.api.delegate.impl;

import com.avilapps.pdf_services.api.delegate.DocumentDelegate;
import com.avilapps.pdf_services.api.model.UploadDocumentRequest;
import com.avilapps.pdf_services.api.model.UploadDocumentResponse;
import com.avilapps.pdf_services.common.exceptions.DelegateException;
import com.avilapps.pdf_services.common.exceptions.RepositoryException;
import com.avilapps.pdf_services.common.exceptions.ServiceException;
import com.avilapps.pdf_services.domain.model.ContentFile;
import com.avilapps.pdf_services.domain.services.DocumentService;
import com.avilapps.pdf_services.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.invoke.MethodHandles;

@Component
@Qualifier("documentUploadDelegate")
public class DocumentUploadDelegate implements DocumentDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final DocumentService documentUploadService;
    private final FileUtils fileUtils;

    public DocumentUploadDelegate(DocumentService documentUploadService, FileUtils fileUtils) {
        this.documentUploadService = documentUploadService;
        this.fileUtils = fileUtils;
    }

    @Override
    public UploadDocumentResponse upload(UploadDocumentRequest request) {
        LOGGER.info("Received file name: " + request.getFile().getOriginalFilename());
        LOGGER.info("Destination path: " + request.getDestinationPath());

        UploadDocumentResponse response = new UploadDocumentResponse();
        try {
            File receivedFile = fileUtils.receiveRemotePDF(request.getFile());
            ContentFile contentFile = new ContentFile();
            contentFile.setContent(receivedFile);
            contentFile.setUrl(request.getDestinationPath());

            String uploadedFileUrl = documentUploadService.upload(contentFile);
            response.setUrl(uploadedFileUrl);

            receivedFile.deleteOnExit();
        } catch (ServiceException | RepositoryException exception) {
            throw exception;
        } catch (Exception exception) {
            LOGGER.error("Error uploading file: " + exception.getMessage(), exception);
            throw new DelegateException(exception);
        }

        return response;
    }
}
