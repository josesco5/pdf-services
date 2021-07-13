package com.avilapps.pdf_services.common.exceptions;

public class RepositoryException extends RuntimeException {

    public RepositoryException(Exception exception) {
        super(exception);
    }
}
