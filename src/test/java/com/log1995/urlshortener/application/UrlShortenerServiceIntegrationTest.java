package com.log1995.urlshortener.application;

import com.log1995.urlshortener.domain.UrlShortenerRepository;
import com.log1995.urlshortener.presentation.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

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

    @Test
    void 단축URL을_조회하면_조회_횟수가_1씩_증가한다() {
        UserDto userDto = new UserDto();
        userDto.setOriginUrl("https://www.naver.com");

        urlShortenerService.changeUrl(userDto);
        urlShortenerService.findOriginUrl(userDto.getChangedUrl());

        int responseCount = urlShortenerService.findResponseCount("localhost:8080/" + userDto.getChangedUrl());
        assertTrue(responseCount == 1);
    }

}
