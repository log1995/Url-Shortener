package com.log1995.urlshortener.service;

import com.log1995.urlshortener.domain.User;
import com.log1995.urlshortener.repository.UrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlShortenerService {

    private final UrlShortenerRepository memoryUrlShortenerRepository;

    private final String localHost = "localhost:8080/";
    private static final int START = 48;
    private static final int END = 122;
    private static final int LIMIT = 10;
    private final int INIT_COUNT = 0;

    @Autowired
    public UrlShortenerService(UrlShortenerRepository memoryUrlShortenerRepository) {
        this.memoryUrlShortenerRepository = memoryUrlShortenerRepository;
    }

    public String checkUrl(String url) {
        if(url.indexOf("http") == 0)
            return url;
        return "http://" + url;
    }

    public String changeUrl(String originUrl) {
        String randomUrl = makeRandomUrl(originUrl);
        User user = new User();
        user.setOriginUrl(originUrl);
        user.setChangedUrl(randomUrl);
        user.setResponseTime(INIT_COUNT);

        saveUrl(user);

        return localHost + randomUrl;
    }

    private String makeRandomUrl(String url) {
        Random random = new Random();
        String randomString = random.ints(START, END + 1)
                .filter(i -> isRightRange(i))
                .limit(LIMIT)
                .collect(StringBuffer::new, StringBuffer::appendCodePoint, StringBuffer::append)
                .toString();

        return randomString;
    }

    private boolean isRightRange(int i) {
        return (i <= 57 || i >= 65) && (i <= 90 || i >= 97);
    }

    private void saveUrl(User user) {
        memoryUrlShortenerRepository.saveUrl(user);
    }

    public String findUrl(String changedUrl) {
        return memoryUrlShortenerRepository.findUrl(changedUrl);
    }

    public int findResponseCount(String url) {
        String[] uri = url.split("/");
        return memoryUrlShortenerRepository.findResponseCount(uri[1]);
    }




































//    @Autowired
//    public UrlShortenerService(UrlShortenerRepository memoryUrlShortenerRepository) {
//        this.memoryUrlShortenerRepository = memoryUrlShortenerRepository;
//    }
//
//    public String changeUrl(String url) {
//        String randomUrl = makeRandomUrl(url);
//        saveUrl(url, randomUrl);
//
//        return localHost + randomUrl;
//    }
//
//    public String checkUrl(String url){
//        if(url.indexOf("http") == 0)
//            return url;
//        return "http://" + url;
//    }
//
//    private String makeRandomUrl(String url) {
//        Random random = new Random();
//        String randomString = random.ints(START, END + 1)
//                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
//                .limit(LIMIT)
//                .collect(StringBuffer::new, StringBuffer::appendCodePoint, StringBuffer::append)
//                .toString();
//
//        return randomString;
//    }
//
//    private void saveUrl(String userUrl, String changedUrl) {
//        memoryUrlShortenerRepository.saveUrl(userUrl, changedUrl);
//    }
//
//    public String findUrl(String url) {
//        return memoryUrlShortenerRepository.findUrl(url);
//    }
//
//    public int findResponseCount(String url) {
//        String[] uri = url.split("/");
//        return memoryUrlShortenerRepository.findResponseCount(uri[1]);
//    }
//

}
