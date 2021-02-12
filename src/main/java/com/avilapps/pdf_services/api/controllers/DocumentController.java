package com.avilapps.pdf_services.api.controllers;

import com.avilapps.pdf_services.api.delegate.DocumentDelegate;
import com.avilapps.pdf_services.api.model.FoliateDocumentRequest;
import com.avilapps.pdf_services.api.model.FoliateDocumentResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentDelegate documentFoliateDelegate;

    public DocumentController(DocumentDelegate documentFoliateDelegate) {
        this.documentFoliateDelegate = documentFoliateDelegate;
    }

    @PostMapping("/foliate")
    public FoliateDocumentResponse foliate(@RequestBody FoliateDocumentRequest request) {
        return documentFoliateDelegate.foliate(request);
    }
}
