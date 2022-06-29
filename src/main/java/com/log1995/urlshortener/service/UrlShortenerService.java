package com.log1995.urlshortener.service;

import com.log1995.urlshortener.repository.UrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlShortenerService {

    private final UrlShortenerRepository memoryUrlShortenerRepository;
    private final String localHost = "localhost:8080/";

    @Autowired
    public UrlShortenerService(UrlShortenerRepository memoryUrlShortenerRepository) {
        this.memoryUrlShortenerRepository = memoryUrlShortenerRepository;
    }

    public String changeUrl(String url) {
        String randomUrl = makeRandomUrl(url);
        saveUrl(url, randomUrl);

        return localHost + randomUrl;
    }

    public String checkingUrl(String url){
        if(url.indexOf("http") == 0)
            return url;
        return "http://" + url;
    }

    private String makeRandomUrl(String url) {
        int start = 48; // letter '0'
        int end = 122; // letter 'z'
        int limitLength = 10;
        Random random = new Random();
        String randomString = random.ints(start, end + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(limitLength)
                .collect(StringBuffer::new, StringBuffer::appendCodePoint, StringBuffer::append)
                .toString();

        return randomString;
    }

    private void saveUrl(String userUrl, String changedUrl) {
        memoryUrlShortenerRepository.saveUrl(userUrl, changedUrl);
    }

    public String findUrl(String url) {
        return memoryUrlShortenerRepository.findUrl(url);
    }

    public int findResponseCount(String url) {
        String[] aa = url.split("/");
        return memoryUrlShortenerRepository.findResponseCount(aa[1]);
    }


}
