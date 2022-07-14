package com.log1995.urlshortener.domain;

public interface UrlShortenerRepository {

    void saveUrl(User user);

    User findOriginUrlInUser(String changedUrl);

    User findResponseCountInUser(String changedUrl);

    User findChangedUrlInUser(String originUrl);
}
