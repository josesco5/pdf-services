package com.avilapps.pdf_services.api.delegate.impl;

import com.avilapps.pdf_services.api.delegate.DocumentDelegate;
import com.avilapps.pdf_services.api.mapper.FoliateDocumentApiMapper;
import com.avilapps.pdf_services.api.model.FoliateDocumentRequest;
import com.avilapps.pdf_services.api.model.FoliateDocumentResponse;
import com.avilapps.pdf_services.common.exceptions.DelegateException;
import com.avilapps.pdf_services.domain.model.Document;
import com.avilapps.pdf_services.domain.services.DocumentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("documentFoliateDelegate")
public class DocumentFoliateDelegate implements DocumentDelegate {

    private final DocumentService documentService;
    private final FoliateDocumentApiMapper foliateDocumentApiMapper;

    public DocumentFoliateDelegate(DocumentService documentService, FoliateDocumentApiMapper foliateDocumentApiMapper) {
        this.documentService = documentService;
        this.foliateDocumentApiMapper = foliateDocumentApiMapper;
    }

    @Override
    public FoliateDocumentResponse foliate(FoliateDocumentRequest request) {
        try {
            Document document = foliateDocumentApiMapper.mapApiRequestToDomainModel(request);
            Document resultDocument = documentService.foliate(document);
            return foliateDocumentApiMapper.mapDomainModelToApiResponse(resultDocument);

        } catch (Exception exception) {
            throw new DelegateException(exception);
        }
    }
}
