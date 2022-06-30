package com.log1995.urlshortener.repository;

import com.log1995.urlshortener.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryUrlShortenerRepository implements UrlShortenerRepository{

    private User user;
    private static List<User> urlList = new ArrayList<>();

    @Override
    public void saveUrl(User user) {
        urlList.add(user);
    }

    @Override
    public String findUrl(String changedUrl) {
       for(User user : urlList) {
           if(user.getChangedUrl().equals(changedUrl)) {
               user.setResponseTime(user.getResponseTime() + 1);
               this.user = user;
           }
       }
      return user.getOriginUrl();
    }

    @Override
    public int findResponseCount(String url) {
        for(User user : urlList) {
            if(user.getChangedUrl().equals(url)) {
                this.user = user;
            }
        }
        return user.getResponseTime();
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
