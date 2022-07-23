package com.log1995.urlshortener.infrastructure;

import com.log1995.urlshortener.domain.UrlShortenerRepository;
import com.log1995.urlshortener.exception.CannotFoundUrlException;
import com.log1995.urlshortener.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("test")
@Repository
public class MemoryUrlShortenerRepository implements UrlShortenerRepository {

//    private User user;  // -> 위험한 코드 , 빈이 상태를 가지는 것. 공유 자원이 됨. Race Condition
    private static List<User> urlList = new ArrayList<>();

    @Override
    public void saveUrl(User user) {
        urlList.add(user);
    }

    @Override
    public User findUserByChangedUrl(String changedUrl) {
        User user = urlList.stream()
                .filter(url -> url.getChangedUrl().equals(changedUrl))
                .findFirst()
                .orElseThrow(() -> {
                    throw new CannotFoundUrlException();
                });

      return user;
    }

    @Override
    public User findUserByOriginUrl(String originUrl) {
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


























//    private static final int INIT = 0;
//
//    private Map<String, String> urlRepository = new HashMap<>();
//    private Map<String, Integer> responseTime = new HashMap<>();
//
//    @Override
//    public void saveUrl(String userUrl, String changedUrl) {
//        urlRepository.put(changedUrl, userUrl);
//        responseTime.put(changedUrl, INIT);
//    }
//
//    @Override
//    public String findUrl(String url) {
//        responseTime.put(url, responseTime.get(url) + 1);
//        return urlRepository.get(url);
//    }
//
//    @Override
//    public int findResponseCount(String url) {
//        return responseTime.get(url);
//    }

}
