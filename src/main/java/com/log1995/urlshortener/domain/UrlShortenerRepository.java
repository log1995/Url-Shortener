package com.log1995.urlshortener.domain;

import java.util.List;

public interface UrlShortenerRepository {

    void saveShortenUrlInfo(ShortenUrlInfo shortenUrlInfo);

    List<ShortenUrlInfo> findShortenUrlInfoByChangedUrl(String changedUrl);

}
