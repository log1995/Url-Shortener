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

        urlShortenerService.changeUrl(shortenUrlResponseDto);
        // 변경 + 저장

        String originUrl = urlShortenerService.findOriginUrl(shortenUrlResponseDto.getChangedUrl());
        assertThat(shortenUrlResponseDto.getOriginUrl()).isEqualTo(originUrl);
    }

    @Test
    void 단축URL을_조회하면_조회_횟수가_1씩_증가한다() {
        ShortenUrlResponseDto shortenUrlResponseDto = new ShortenUrlResponseDto();
        shortenUrlResponseDto.setOriginUrl("https://www.naver.com");

        urlShortenerService.changeUrl(shortenUrlResponseDto);
        urlShortenerService.findOriginUrl(shortenUrlResponseDto.getChangedUrl());

        int viewCount = urlShortenerService.findViewCount("localhost:8080/" + shortenUrlResponseDto.getChangedUrl());
        assertTrue(viewCount == 1);
    }

    @Test
    void 단축URL을_생성하여_중복검사를_하면_true를_반환한다() {
        ShortenUrlResponseDto shortenUrlResponseDto = new ShortenUrlResponseDto();
        shortenUrlResponseDto.setOriginUrl("https://www.naver.com");

        urlShortenerService.changeUrl(shortenUrlResponseDto);

        boolean isSameChangedUrl = urlShortenerRepository.findSameChangedUrlInRepository(shortenUrlResponseDto.getChangedUrl());
        assertTrue(isSameChangedUrl == true);
    }

}
