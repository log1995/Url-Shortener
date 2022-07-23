package com.log1995.urlshortener.application;

import com.log1995.urlshortener.domain.UrlShortenerRepository;

import com.log1995.urlshortener.domain.User;
import com.log1995.urlshortener.presentation.UserDto;
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
    public void save() {
        // Given
        String ORIGIN_URL = "https://www.naver.com";

        UserDto userDto = new UserDto();
        userDto.setOriginUrl(ORIGIN_URL);
        
        // when
         when(urlShortenerRepository.findUserByChangedUrl(any())).thenReturn(new User(ORIGIN_URL, "1q2w3e4r", 0));
         String originUrl = urlShortenerService.findOriginUrl("ddd");

        // then
         assertThat(userDto.getOriginUrl()).isEqualTo(originUrl);
    }

    @Test
    public void findResponseCount에_changedUrl로_조회하면_responseTime을_조회할_수_있다() {
        // Given
        String ORIGIN_URL = "https://www.naver.com";
        String CHANGED_URL = "1q2w3e4r";
        int RESPONSE_COUNT = 1;

        UserDto userDto = new UserDto();
        userDto.setResponseTime(RESPONSE_COUNT);

        when(urlShortenerRepository.findUserByChangedUrl(CHANGED_URL))
                .thenReturn(new User(ORIGIN_URL, CHANGED_URL, RESPONSE_COUNT));

        int responseTime = urlShortenerService.findResponseCount("ddd/" + CHANGED_URL);

        assertTrue(userDto.getResponseTime() == responseTime);
    }

//    @Test
//    void 단축URL을_조회하면_조회_횟수가_1씩_증가한다() {
//        UserDto userDto = new UserDto();
//        userDto.setOriginUrl("https://www.naver.com");
//
//        urlShortenerService.changeUrl(userDto);
//        urlShortenerService.findOriginUrl(userDto.getChangedUrl());
//
//        int responseCount = urlShortenerService.findResponseCount("localhost:8080/" + userDto.getChangedUrl());
//        assertThat(userDto.getResponseTime() + 10000 == responseCount);
//    }

}
