package com.avilapps.pdf_services.api.delegate;

import com.avilapps.pdf_services.api.model.FoliateDocumentRequest;
import com.avilapps.pdf_services.api.model.FoliateDocumentResponse;
import com.avilapps.pdf_services.common.exceptions.NonImplementedException;

public interface DocumentDelegate {

    default FoliateDocumentResponse upload(FoliateDocumentRequest request) {
        throw new NonImplementedException();
    }

    default FoliateDocumentResponse foliate(FoliateDocumentRequest request) {
        throw new NonImplementedException();
    }
}
