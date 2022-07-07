package com.log1995.urlshortener.service;

import com.log1995.urlshortener.controller.UserDTO;
import com.log1995.urlshortener.domain.User;
import com.log1995.urlshortener.exception.NotContainHttpInUrlException;
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

    @Autowired
    public UrlShortenerService(UrlShortenerRepository memoryUrlShortenerRepository) {
        this.memoryUrlShortenerRepository = memoryUrlShortenerRepository;
    }

    public void checkUrl(String url) {
        if(url.indexOf("http") == -1) {
            throw new NotContainHttpInUrlException();
        }
    }

    public String changeUrl(UserDTO userDTO) {
        userDTO.setChangedUrl(makeRandomUrl(userDTO.getOriginUrl()));
        User user = userDTO.dtoToEntity();
        saveUrl(user);

        return localHost + userDTO.getChangedUrl();

//        String randomUrl = makeRandomUrl(userDTO.getOriginUrl());
//        User user = new User(userDTO.getOriginUrl(), randomUrl, INIT_COUNT);
//        saveUrl(user);
//        return localHost + randomUrl;
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

    public String findOriginUrl(String changedUrl) {
        UserDTO userDTO = new UserDTO(memoryUrlShortenerRepository.findOriginUrlInUser(changedUrl));
        return userDTO.getOriginUrl();
    }

    public int findResponseCount(String changedUrl) {
        String[] uri = changedUrl.split("/");
        UserDTO userDTO = new UserDTO(memoryUrlShortenerRepository.findResponseCountInUser(uri[1]));
        return userDTO.getResponseTime();
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
