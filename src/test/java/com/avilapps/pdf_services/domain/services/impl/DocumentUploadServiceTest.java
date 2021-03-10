package com.avilapps.pdf_services.domain.services.impl;

import com.avilapps.pdf_services.common.exceptions.RepositoryException;
import com.avilapps.pdf_services.common.exceptions.ServiceException;
import com.avilapps.pdf_services.domain.model.ContentFile;
import com.avilapps.pdf_services.domain.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DocumentUploadServiceTest {

    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private DocumentUploadService documentUploadService;

    @Test
    public void whenUploadingDocumentThrowsRepositoryException() {
        ContentFile contentFile = new ContentFile();
        Mockito.when(fileRepository.upload(contentFile))
                .thenThrow(RepositoryException.class);

        assertThrows(RepositoryException.class, () -> documentUploadService.upload(contentFile));
    }

    @Test
    public void whenUploadingDocumentThrowsNullPointerException() {
        ContentFile contentFile = new ContentFile();
        Mockito.when(fileRepository.upload(contentFile))
                .thenThrow(NullPointerException.class);

        assertThrows(ServiceException.class, () -> documentUploadService.upload(contentFile));
    }
}