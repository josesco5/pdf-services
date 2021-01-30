package com.avilapps.pdf_services.domain.services.impl;

import com.avilapps.pdf_services.common.exceptions.ServiceException;
import com.avilapps.pdf_services.domain.model.ContentFile;
import com.avilapps.pdf_services.domain.model.Document;
import com.avilapps.pdf_services.domain.repository.FileRepository;
import com.avilapps.pdf_services.domain.services.Foliator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DocumentFoliateServiceTest {

    @Mock
    private Foliator foliator;

    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private DocumentFoliateService documentFoliateService;

    private Document document;
    private File file;

    @BeforeEach
    void setUp() {
        document = new Document();
        ContentFile originalFile = new ContentFile();
        originalFile.setUrl("");
        ContentFile processedFile = new ContentFile();
        processedFile.setUrl("");
        document.setOriginalFile(originalFile);
        document.setProcessedFile(processedFile);
        document.setInitialFolio(1);

        file = new File("src/test/resources");
    }

    @Test
    public void whenFoliatingFileReturnsDocument() throws IOException {
        String fileUrl = "http://localhost/file.pdf";

        Mockito.when(foliator.foliate(document.getOriginalFile().getUrl(), document.getInitialFolio()))
                .thenReturn(file);
        Mockito.when(fileRepository.upload(document.getProcessedFile()))
                .thenReturn(fileUrl);

        Document result = documentFoliateService.foliate(document);

        assertEquals(fileUrl, result.getProcessedFile().getUrl());
    }

    @Test
    public void whenFoliatingFileThrowsException() throws IOException {
        String fileUrl = "http://localhost/file.pdf";

        Mockito.when(foliator.foliate(document.getOriginalFile().getUrl(), document.getInitialFolio()))
                .thenThrow(NullPointerException.class);
        Mockito.when(fileRepository.upload(document.getProcessedFile()))
                .thenReturn(fileUrl);

        assertThrows(ServiceException.class, () -> documentFoliateService.foliate(document));
    }

    @Test
    public void whenUploadingFileThrowsException() throws IOException {
        String fileUrl = "http://localhost/file.pdf";

        Mockito.when(foliator.foliate(document.getOriginalFile().getUrl(), document.getInitialFolio()))
                .thenReturn(file);
        Mockito.when(fileRepository.upload(document.getProcessedFile()))
                .thenThrow(NullPointerException.class);

        assertThrows(ServiceException.class, () -> documentFoliateService.foliate(document));
    }
}