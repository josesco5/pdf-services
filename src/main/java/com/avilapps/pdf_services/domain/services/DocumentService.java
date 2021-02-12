package com.avilapps.pdf_services.domain.services;

import com.avilapps.pdf_services.common.exceptions.NonImplementedException;
import com.avilapps.pdf_services.domain.model.Document;

public interface DocumentService {

    default Document foliate(Document document) {
        throw new NonImplementedException();
    }
}
