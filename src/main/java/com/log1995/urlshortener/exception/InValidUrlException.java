package com.log1995.urlshortener.exception;

public class InValidUrlException extends UrlShortenerException{

    public InValidUrlException() {
        super("URL 형식이 잘못되었습니다. 다시 한번 확인해주세요.");
    }

}
