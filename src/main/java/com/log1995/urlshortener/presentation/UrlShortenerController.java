package com.log1995.urlshortener.presentation;

import com.log1995.urlshortener.application.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    @Autowired
    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

//    // 시작화면
//    @GetMapping("/home")
//    public String home() {
//        return "home";
//    }

    // 단축 URL 생성
    @PostMapping("/change")
    public String changeUserUrlToShortenUrl(@RequestBody ShortenUrlResponseDto shortenUrlResponseDto) {
        urlShortenerService.checkValidationOfUrl(shortenUrlResponseDto.getOriginUrl());
        String changedUrl = urlShortenerService.makeRandomUrl();
        shortenUrlResponseDto.setChangedUrl(changedUrl);
        urlShortenerService.saveUrlInfo(shortenUrlResponseDto);
        return "localhost:8080/" + changedUrl;
    }

    // 단축 URL Redirect
    @GetMapping("/{changedUrl}")
    public ResponseEntity urlRedirect(@PathVariable("changedUrl") String changedUrl) {
        String originUrl = urlShortenerService.findOriginUrl(changedUrl);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(URI.create(originUrl));

        return new ResponseEntity("Redirect to " + originUrl, responseHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    // 단축 URL 요청 횟수
    @GetMapping("/count")
    public int returnResponseCount(@RequestParam("url") String changedUrl) {
        return urlShortenerService.findViewCount(changedUrl);
    }

}
