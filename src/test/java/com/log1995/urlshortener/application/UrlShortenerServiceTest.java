package com.log1995.urlshortener.application;

import com.log1995.urlshortener.domain.UrlShortenerRepository;

import com.log1995.urlshortener.domain.ShortenUrlInfo;
import com.log1995.urlshortener.exception.TryAgainException;
import com.log1995.urlshortener.presentation.ShortenUrlResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlShortenerServiceTest {

    @Mock
    private UrlShortenerRepository urlShortenerRepository;


    @InjectMocks
    private UrlShortenerService urlShortenerService;

    @Test
    public void findOriginUrl에_CHANGED_URL로_조회하면_originUrl을_조회할_수_있다() {
        // Given
        String ORIGIN_URL = "https://www.naver.com";
        String CHANGED_URL = "1q2w3e4r";
        int VIEW_COUNT = 1;

        List<ShortenUrlInfo> shortenUrlInfoList = new ArrayList<>();
        shortenUrlInfoList.add(new ShortenUrlInfo(ORIGIN_URL, CHANGED_URL, VIEW_COUNT));

        ShortenUrlResponseDto shortenUrlResponseDto = new ShortenUrlResponseDto();
        shortenUrlResponseDto.setOriginUrl(ORIGIN_URL);
        
        // when
         when(urlShortenerRepository.findShortenUrlInfoByChangedUrl(any()))
                 .thenReturn(shortenUrlInfoList);

         String originUrl = urlShortenerService.findOriginUrl(CHANGED_URL);

        // then
         assertThat(shortenUrlResponseDto.getOriginUrl()).isEqualTo(originUrl);
    }

    @Test
    public void findViewCount에_changedUrl로_조회하면_viewCount를_조회할_수_있다() {
        // Given
        String ORIGIN_URL = "https://www.naver.com";
        String CHANGED_URL = "1q2w3e4r";
        int VIEW_COUNT = 1;

        List<ShortenUrlInfo> shortenUrlInfoList = new ArrayList<>();
        shortenUrlInfoList.add(new ShortenUrlInfo(ORIGIN_URL, CHANGED_URL, VIEW_COUNT));

        ShortenUrlResponseDto shortenUrlResponseDto = new ShortenUrlResponseDto();
        shortenUrlResponseDto.setViewCount(VIEW_COUNT);

        // when
        when(urlShortenerRepository.findShortenUrlInfoByChangedUrl(CHANGED_URL))
                .thenReturn(shortenUrlInfoList);

        int viewCount = urlShortenerService.findViewCount("localhost:8080/" + CHANGED_URL);

        // then
        assertTrue(shortenUrlResponseDto.getViewCount() == viewCount);
    }

    @Test
    public void findShortenUrlInfoByChangedUrl에_리턴_값이_null이_아니면_makeRandomUrl_실행_시_예외가_발생한다() {
        // Given
        String ORIGIN_URL = "https://www.naver.com";
        String CHANGED_URL = "1q2w3e4r";
        int VIEW_COUNT = 1;

        List<ShortenUrlInfo> shortenUrlInfoList = new ArrayList<>();
        shortenUrlInfoList.add(new ShortenUrlInfo(ORIGIN_URL, CHANGED_URL, VIEW_COUNT));

        // when
        when(urlShortenerRepository.findShortenUrlInfoByChangedUrl(any()))
                .thenReturn(shortenUrlInfoList);

        //then
        assertThrows(TryAgainException.class, () -> urlShortenerService.makeRandomUrl());

    }
}
