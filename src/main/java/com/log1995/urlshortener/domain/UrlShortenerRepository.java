package com.log1995.urlshortener.domain;

public interface UrlShortenerRepository {

    void saveUrl(ShortenUrl shortenUrl);

    ShortenUrl findUserByChangedUrl(String changedUrl);

    ShortenUrl findUserByOriginUrl(String originUrl);

    boolean findSameChangedUrlInRepository(String changedUrl);
}
