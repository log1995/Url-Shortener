package com.log1995.urlshortener.exception;

public class TryAgainException extends UrlShortenerException {

    public TryAgainException() {
        super("다시 시도해주세요.");
    }
}
