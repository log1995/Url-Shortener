package com.log1995.urlshortener.repository;

public interface UrlShortenerRepository {

    void saveUrl(String userUrl, String changedUrl);

    String findUrl(String url);

    int findResponseCount(String url);
}
