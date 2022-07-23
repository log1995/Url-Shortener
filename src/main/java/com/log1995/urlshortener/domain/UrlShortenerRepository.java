package com.log1995.urlshortener.domain;

public interface UrlShortenerRepository {

    void saveUrl(User user);

    User findUserByChangedUrl(String changedUrl);

    User findUserByOriginUrl(String originUrl);
}
