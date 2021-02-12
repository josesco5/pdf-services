package com.avilapps.pdf_services.api.delegate.impl;

import com.avilapps.pdf_services.api.mapper.FoliateDocumentApiMapper;
import com.avilapps.pdf_services.api.model.FoliateDocumentRequest;
import com.avilapps.pdf_services.common.exceptions.DelegateException;
import com.avilapps.pdf_services.common.exceptions.ServiceException;
import com.avilapps.pdf_services.domain.model.Document;
import com.avilapps.pdf_services.domain.services.DocumentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DocumentFoliateDelegateTest {

    @Mock
    private DocumentService documentService;

    @Mock
    private FoliateDocumentApiMapper foliateDocumentApiMapper;

    @InjectMocks
    private DocumentFoliateDelegate documentFoliateDelegate;

    @Test
    public void whenFoliatingPDFThenThrowsServiceException() {
        FoliateDocumentRequest request = new FoliateDocumentRequest();
        Document document = new Document();
        Mockito.when(foliateDocumentApiMapper.mapApiRequestToDomainModel(request))
                .thenReturn(document);
        Mockito.when(documentService.foliate(document))
                .thenThrow(ServiceException.class);

        assertThrows(ServiceException.class, () -> documentFoliateDelegate.foliate(request));
    }

    @Test
    public void whenMappingResponseThenThrowsException() {
        FoliateDocumentRequest request = new FoliateDocumentRequest();
        Document document = new Document();
        Document processedDocument = new Document();
        Mockito.when(foliateDocumentApiMapper.mapApiRequestToDomainModel(request))
                .thenReturn(document);
        Mockito.when(documentService.foliate(document))
                .thenReturn(processedDocument);
        Mockito.when(foliateDocumentApiMapper.mapDomainModelToApiResponse(processedDocument))
                .thenThrow(NullPointerException.class);

        assertThrows(DelegateException.class, () -> documentFoliateDelegate.foliate(request));
    }
}
