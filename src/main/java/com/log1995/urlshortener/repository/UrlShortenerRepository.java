package com.log1995.urlshortener.repository;

import com.log1995.urlshortener.domain.User;

public interface UrlShortenerRepository {

    void saveUrl(User user);

    String findUrl(String url);

    int findResponseCount(String url);
}
