package com.log1995.urlshortener.controller;

import com.log1995.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    @PostMapping("/makeShortenUrl")
    public String changeUrl(@RequestBody String userUrl) {
        String checkedUrl = urlShortenerService.checkingUrl(userUrl);
        return urlShortenerService.changeUrl(checkedUrl);
    }

    // 단축 URL Redirect
    @GetMapping("/{changedUrl}")
    public RedirectView returnOriginalUrl(@PathVariable("changedUrl") String changeUrl) {
        RedirectView redirectView = new RedirectView();

        String originUrl = urlShortenerService.findUrl(changeUrl);
        redirectView.setUrl(originUrl);

        return redirectView;
    }

    // 단축 URL 요청 횟수
    @PostMapping("/returnResponseCount")
    public int returnResponseCount(@RequestBody String url) {
        return urlShortenerService.findResponseCount(url);
    }

}
