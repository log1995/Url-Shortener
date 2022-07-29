package com.log1995.urlshortener.infrastructure;

import com.log1995.urlshortener.domain.UrlShortenerRepository;
import com.log1995.urlshortener.exception.CannotFoundUrlException;
import com.log1995.urlshortener.domain.ShortenUrl;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("test")
@Repository
public class MemoryUrlShortenerRepository implements UrlShortenerRepository {

//    private User user;  // -> 위험한 코드 , 빈이 상태를 가지는 것. 공유 자원이 됨. Race Condition
    private static List<ShortenUrl> urlList = new ArrayList<>();

    @Override
    public void saveUrl(ShortenUrl shortenUrl) {
        urlList.add(shortenUrl);
    }

    @Override
    public ShortenUrl findUserByChangedUrl(String changedUrl) {
        ShortenUrl shortenUrl = urlList.stream()
                .filter(url -> url.getChangedUrl().equals(changedUrl))
                .findFirst()
                .orElseThrow(() -> {
                    throw new CannotFoundUrlException();
                });

      return shortenUrl;
    }

    @Override
    public ShortenUrl findUserByOriginUrl(String originUrl) {
//        user = null;
//
//        for(User user : urlList) {
//            if(user.getChangedUrl().equals(originUrl)) {
//                this.user = user;
//            }
//        }
//
//        if(user == null) {
//            throw new CannotFoundUrlException();
//        }

        return null;
    }

    @Override
    public boolean findSameChangedUrlInRepository(String changedUrl) {
        boolean isSameChangedurl = urlList.stream()
                .anyMatch(url -> url.getChangedUrl().equals(changedUrl));
        return isSameChangedurl;
    }
}
