package com.avilapps.pdf_services.common.exceptions;

public class DelegateException extends RuntimeException {

    public DelegateException(Exception exception) {
        super(exception);
    }
}
