package com.log1995.urlshortener.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryUrlShortenerRepository implements UrlShortenerRepository{

    private static final int INIT = 0;

    private Map<String, String> urlRepository = new HashMap<>();
    private Map<String, Integer> responseTime = new HashMap<>();

    @Override
    public void saveUrl(String userUrl, String changedUrl) {
        urlRepository.put(changedUrl, userUrl);
        responseTime.put(changedUrl, INIT);
    }

    @Override
    public String findUrl(String url) {
        responseTime.put(url, responseTime.get(url) + 1);
        return urlRepository.get(url);
    }

    @Override
    public int findResponseCount(String url) {
        return responseTime.get(url);
    }

}
