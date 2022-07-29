package com.log1995.urlshortener.application;

import com.log1995.urlshortener.domain.UrlShortenerRepository;

import com.log1995.urlshortener.domain.ShortenUrl;
import com.log1995.urlshortener.presentation.ShortenUrlResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlShortenerServiceTest {

    @Mock
    private UrlShortenerRepository urlShortenerRepository;


    @InjectMocks
    private UrlShortenerService urlShortenerService;

    @Test
    public void findOriginUrl에_CHANGED_URL로_조회하면_originUrl을_죄회할_수_있다() {
        // Given
        String ORIGIN_URL = "https://www.naver.com";
        String CHANGED_URL = "1q2w3e4r";

        ShortenUrlResponseDto shortenUrlResponseDto = new ShortenUrlResponseDto();
        shortenUrlResponseDto.setOriginUrl(ORIGIN_URL);
        
        // when
         when(urlShortenerRepository.findUserByChangedUrl(any()))
                 .thenReturn(new ShortenUrl(ORIGIN_URL, CHANGED_URL, 0));

         String originUrl = urlShortenerService.findOriginUrl(CHANGED_URL);

        // then
         assertThat(shortenUrlResponseDto.getOriginUrl()).isEqualTo(originUrl);
    }

    @Test
    public void findResponseCount에_changedUrl로_조회하면_viewCount를_조회할_수_있다() {
        // Given
        String ORIGIN_URL = "https://www.naver.com";
        String CHANGED_URL = "1q2w3e4r";
        int RESPONSE_COUNT = 1;

        ShortenUrlResponseDto shortenUrlResponseDto = new ShortenUrlResponseDto();
        shortenUrlResponseDto.setViewCount(RESPONSE_COUNT);

        // when
        when(urlShortenerRepository.findUserByChangedUrl(CHANGED_URL))
                .thenReturn(new ShortenUrl(ORIGIN_URL, CHANGED_URL, RESPONSE_COUNT));

        int viewCount = urlShortenerService.findViewCount("localhost:8080/" + CHANGED_URL);

        // then
        assertTrue(shortenUrlResponseDto.getViewCount() == viewCount);
    }

    @Test
    public void 중복된_단축URL() {
        // Given
        String ORIGIN_URL = "https://www.naver.com";
        String CHANGED_URL = "1q2w3e4r";
        int RESPONSE_COUNT = 1;

        // when
        when(urlShortenerRepository.findSameChangedUrlInRepository(CHANGED_URL)).thenReturn(false);


    }


//    @Test
//    void 생성된_단축URL을_조회해서_중복이면_true를_반환한다() {
//        ShortenUrlResponseDto shortenUrlResponseDto = new ShortenUrlResponseDto();
//        shortenUrlResponseDto.setOriginUrl("https://www.naver.com");
//
//        urlShortenerService.changeUrl(shortenUrlResponseDto);
//
//        boolean isSameChangedUrl = urlShortenerRepository.findSameChangedUrlInRepository(shortenUrlResponseDto.getChangedUrl());
//        assertTrue(isSameChangedUrl == true);
//
//    }


}
