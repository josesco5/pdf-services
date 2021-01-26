package com.avilapps.pdf_services.common.exceptions;

public class ServiceException extends RuntimeException {

    public ServiceException(Exception exception) {
        super(exception);
    }
}
