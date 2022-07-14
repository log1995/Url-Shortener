package com.log1995.urlshortener.application;

import com.log1995.urlshortener.domain.UrlShortenerRepository;
import com.log1995.urlshortener.domain.User;
import com.log1995.urlshortener.presentation.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Profile("prod")
@SpringBootTest
@Transactional
class UrlShortenerServiceIntegrationTest {

    @Autowired UrlShortenerService urlShortenerService;
    @Autowired UrlShortenerRepository urlShortenerRepository;

    @Test
    void 원본URL을_단축하고_조회하면_원본URL이_조회된다() {
        UserDto userDto = new UserDto();
        userDto.setOriginUrl("https://www.naver.com");

        urlShortenerService.changeUrl(userDto);
        // 변경 + 저장

//        String changedUrl = urlShortenerService.findChangedUrl(userDto.getOriginUrl());

        String originUrl = urlShortenerService.findOriginUrl(userDto.getChangedUrl());
        assertThat(userDto.getOriginUrl()).isEqualTo(originUrl);
    }

}
