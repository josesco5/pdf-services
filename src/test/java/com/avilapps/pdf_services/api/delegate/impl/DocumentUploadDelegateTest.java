package com.avilapps.pdf_services.api.delegate.impl;

import com.avilapps.pdf_services.api.model.UploadDocumentRequest;
import com.avilapps.pdf_services.common.exceptions.DelegateException;
import com.avilapps.pdf_services.common.exceptions.ServiceException;
import com.avilapps.pdf_services.domain.model.ContentFile;
import com.avilapps.pdf_services.domain.services.DocumentService;
import com.avilapps.pdf_services.utils.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DocumentUploadDelegateTest {

    @Mock
    private DocumentService documentService;

    @Mock
    private FileUtils fileUtils;

    @InjectMocks
    private DocumentUploadDelegate documentUploadDelegate;

    private UploadDocumentRequest uploadDocumentRequest;
    private File file;

    @BeforeEach
    void setUp() {
        MockMultipartFile sentFile = new MockMultipartFile(
                "file", "test.pdf", MediaType.APPLICATION_PDF_VALUE,
                "Test content".getBytes()
        );
        uploadDocumentRequest = new UploadDocumentRequest();
        uploadDocumentRequest.setDestinationPath("/upload/file.pdf");
        uploadDocumentRequest.setFile(sentFile);

        file = new File("src/test/resources");
    }

    @Test
    public void whenUploadingDocumentThenThrowsServiceException() {

        Mockito.when(fileUtils.receiveRemotePDF(uploadDocumentRequest.getFile()))
                .thenReturn(file);
        Mockito.when(documentService.upload(Mockito.any(ContentFile.class)))
                .thenThrow(ServiceException.class);

        assertThrows(ServiceException.class, () -> documentUploadDelegate.upload(uploadDocumentRequest));

        Mockito.verify(fileUtils).receiveRemotePDF(uploadDocumentRequest.getFile());
        Mockito.verify(documentService).upload(Mockito.any(ContentFile.class));
    }

    @Test
    public void whenUploadingDocumentThenThrowsDelegateException() {

        Mockito.when(fileUtils.receiveRemotePDF(uploadDocumentRequest.getFile()))
                .thenReturn(file);
        Mockito.when(documentService.upload(Mockito.any(ContentFile.class)))
                .thenThrow(NullPointerException.class);

        assertThrows(DelegateException.class, () -> documentUploadDelegate.upload(uploadDocumentRequest));

        Mockito.verify(fileUtils).receiveRemotePDF(uploadDocumentRequest.getFile());
        Mockito.verify(documentService).upload(Mockito.any(ContentFile.class));
    }
}
