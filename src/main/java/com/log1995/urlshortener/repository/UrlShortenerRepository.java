package com.log1995.urlshortener.repository;

import com.log1995.urlshortener.domain.User;

public interface UrlShortenerRepository {

    void saveUrl(User user);

    User findOriginUrlInUser(String url);

    User findResponseCountInUser(String url);
}
