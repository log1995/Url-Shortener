package com.log1995.urlshortener.application;

import com.log1995.urlshortener.presentation.UserDto;
import com.log1995.urlshortener.domain.User;
import com.log1995.urlshortener.exception.NotContainHttpInUrlException;
import com.log1995.urlshortener.domain.UrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Transactional
@Service
public class UrlShortenerService {

    private final UrlShortenerRepository urlShortenerRepository;

    private final String localHost = "localhost:8080/";
    private static final int START = 48;
    private static final int END = 122;
    private static final int LIMIT = 10;

    @Autowired
    public UrlShortenerService(UrlShortenerRepository urlShortenerRepository) {
        this.urlShortenerRepository = urlShortenerRepository;
    }

    public void checkUrl(String url) {
        if(url.indexOf("http") == -1) {
            throw new NotContainHttpInUrlException();
        }
    }

    public String changeUrl(UserDto userDTO) {
        userDTO.setChangedUrl(makeRandomUrl(userDTO.getOriginUrl()));
        User user = userDTO.dtoToEntity();
        saveUrl(user);

        return localHost + userDTO.getChangedUrl();
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
        urlShortenerRepository.saveUrl(user);
    }

    public String findOriginUrl(String changedUrl) {
        UserDto userDTO = new UserDto(urlShortenerRepository.findOriginUrlInUser(changedUrl));
        return userDTO.getOriginUrl();
    }

    public int findResponseCount(String changedUrl) {
        String[] uri = changedUrl.split("/");
        UserDto userDTO = new UserDto(urlShortenerRepository.findResponseCountInUser(uri[1]));
        return userDTO.getResponseTime();
    }

    public String findChangedUrl(String originUrl) {
        UserDto userDto = new UserDto(urlShortenerRepository.findChangedUrlInUser(originUrl));
        return userDto.getChangedUrl();
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
