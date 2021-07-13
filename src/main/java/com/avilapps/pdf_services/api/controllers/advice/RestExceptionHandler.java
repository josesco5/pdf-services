package com.avilapps.pdf_services.api.controllers.advice;

import com.avilapps.pdf_services.api.model.ApiError;
import com.avilapps.pdf_services.common.exceptions.DelegateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DelegateException.class)
    protected ResponseEntity<Object> handleDelegateException(RuntimeException exception, WebRequest request) {
        String message = String.format("DELEGATE_EXCEPTION: %s", exception.getMessage());
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message, LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
