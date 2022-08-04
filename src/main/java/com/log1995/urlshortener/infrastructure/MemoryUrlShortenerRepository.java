package com.log1995.urlshortener.infrastructure;

import com.log1995.urlshortener.domain.UrlShortenerRepository;
import com.log1995.urlshortener.domain.ShortenUrlInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Profile("test")
@Repository
public class MemoryUrlShortenerRepository implements UrlShortenerRepository {

//    private User user; --> 위험한 코드 , 빈이 상태를 가지는 것. 공유 자원이 됨. Race Condition
    private static List<ShortenUrlInfo> urlList = new ArrayList<>();

    @Override
    public void saveShortenUrlInfo(ShortenUrlInfo shortenUrlInfo) {
        urlList.add(shortenUrlInfo);
    }

    @Override
    public List<ShortenUrlInfo> findShortenUrlInfoByChangedUrl(String changedUrl) {
        List<ShortenUrlInfo> shortenUrlInfo = urlList.stream()
                .filter(url -> url.getChangedUrl().equals(changedUrl))
                .collect(Collectors.toList());

      return shortenUrlInfo;
    }

}
