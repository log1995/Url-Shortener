package com.log1995.urlshortener.exception;

public class NotContainHttpInUrlException extends UrlShortenerException{

    public NotContainHttpInUrlException() {
        super("URL에 http를 붙여주세요.");
    }

}
