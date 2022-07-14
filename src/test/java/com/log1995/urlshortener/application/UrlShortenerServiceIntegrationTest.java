package com.log1995.urlshortener.application;

import com.log1995.urlshortener.domain.UrlShortenerRepository;
import com.log1995.urlshortener.domain.User;
import com.log1995.urlshortener.presentation.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UrlShortenerServiceIntegrationTest {

    @Autowired UrlShortenerService urlShortenerService;
    @Autowired UrlShortenerRepository urlShortenerRepository;

    @Test
    void 단축URL저장() {
        UserDto userDto = new UserDto();
        userDto.setOriginUrl("https://www.naver.com");

        urlShortenerService.changeUrl(userDto);

        String changedUrl = urlShortenerService.findChangedUrl(userDto.getOriginUrl());

        String originUrl = urlShortenerService.findOriginUrl(changedUrl);
        assertThat(userDto.getOriginUrl()).isEqualTo(originUrl);
    }

}
