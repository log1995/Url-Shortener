package com.log1995.urlshortener.application;

import com.log1995.urlshortener.domain.UrlShortenerRepository;
import com.log1995.urlshortener.presentation.ShortenUrlResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@Profile("test")
@SpringBootTest
@Transactional
class UrlShortenerServiceIntegrationTest {

    @Autowired UrlShortenerService urlShortenerService;
    @Autowired UrlShortenerRepository urlShortenerRepository;

    @Test
    void 원본URL을_단축하고_조회하면_원본URL이_조회된다() {
        ShortenUrlResponseDto shortenUrlResponseDto = new ShortenUrlResponseDto();
        shortenUrlResponseDto.setOriginUrl("https://www.naver.com");

        String changedUrl = urlShortenerService.makeRandomUrl();
        shortenUrlResponseDto.setChangedUrl(changedUrl);
        urlShortenerService.saveUrlInfo(shortenUrlResponseDto);
        // 변경 + 저장

        String originUrl = urlShortenerService.findOriginUrl(changedUrl);
        assertThat(shortenUrlResponseDto.getOriginUrl()).isEqualTo(originUrl);
    }

    @Test
    void 단축URL을_조회하면_조회_횟수가_1씩_증가한다() {
        String ORIGIN_URL = "https://www.naver.com";
        String CHANGED_URL = "1q2w3e4r";

        ShortenUrlResponseDto shortenUrlResponseDto = new ShortenUrlResponseDto();
        shortenUrlResponseDto.setOriginUrl(ORIGIN_URL);
        shortenUrlResponseDto.setChangedUrl(CHANGED_URL);

        urlShortenerService.saveUrlInfo(shortenUrlResponseDto);
        urlShortenerService.findOriginUrl(CHANGED_URL);

        int viewCount = urlShortenerService.findViewCount("localhost:8080/" + CHANGED_URL);
        assertTrue(viewCount == 1);
    }

    @Test
    void 단축URL_중복_생성_검사() {
        String ORIGIN_URL = "https://www.naver.com";

        // 객체 1 생성
        ShortenUrlResponseDto shortenUrlResponseDto1 = new ShortenUrlResponseDto();
        shortenUrlResponseDto1.setOriginUrl(ORIGIN_URL);

        String changedUrl1 = urlShortenerService.makeRandomUrl();
        shortenUrlResponseDto1.setChangedUrl(changedUrl1);
        urlShortenerService.saveUrlInfo(shortenUrlResponseDto1);

        // 객체 2 생성
        ShortenUrlResponseDto shortenUrlResponseDto2 = new ShortenUrlResponseDto();
        shortenUrlResponseDto2.setOriginUrl(ORIGIN_URL);

        String changedUrl2 = urlShortenerService.makeRandomUrl();
        shortenUrlResponseDto2.setChangedUrl(changedUrl2);


        assertThat(shortenUrlResponseDto1.getChangedUrl()).isNotEqualTo(shortenUrlResponseDto2.getChangedUrl());
    }

}
