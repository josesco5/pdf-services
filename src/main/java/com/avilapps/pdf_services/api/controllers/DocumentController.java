package com.avilapps.pdf_services.api.controllers;

import com.avilapps.pdf_services.api.delegate.DocumentDelegate;
import com.avilapps.pdf_services.api.model.FoliateDocumentRequest;
import com.avilapps.pdf_services.api.model.FoliateDocumentResponse;
import com.avilapps.pdf_services.api.model.UploadDocumentRequest;
import com.avilapps.pdf_services.api.model.UploadDocumentResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentDelegate documentFoliateDelegate;
    private final DocumentDelegate documentUploadDelegate;

    public DocumentController(DocumentDelegate documentFoliateDelegate, DocumentDelegate documentUploadDelegate) {
        this.documentFoliateDelegate = documentFoliateDelegate;
        this.documentUploadDelegate = documentUploadDelegate;
    }

    @PostMapping("/foliate")
    public FoliateDocumentResponse foliate(@RequestBody FoliateDocumentRequest request) {
        return documentFoliateDelegate.foliate(request);
    }

    @PostMapping("/upload")
    public UploadDocumentResponse upload(@ModelAttribute UploadDocumentRequest uploadDocumentRequest) {
        return documentUploadDelegate.upload(uploadDocumentRequest);
    }
}
