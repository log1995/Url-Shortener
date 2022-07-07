package com.log1995.urlshortener.controller;

import com.log1995.urlshortener.exception.UrlShortenerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlShortenerException.class)
    public ResponseEntity handleUrlShortenerException(UrlShortenerException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

};
