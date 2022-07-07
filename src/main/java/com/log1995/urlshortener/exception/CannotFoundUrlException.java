package com.log1995.urlshortener.exception;

public class CannotFoundUrlException extends UrlShortenerException {

    public CannotFoundUrlException() {
        super("해당 URL은 없는 URL입니다.");
    }

}
