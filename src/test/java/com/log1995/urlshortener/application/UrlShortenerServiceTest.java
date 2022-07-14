package com.log1995.urlshortener.application;

import com.log1995.urlshortener.domain.UrlShortenerRepository;

import com.log1995.urlshortener.domain.User;
import com.log1995.urlshortener.infrastructure.DataBaseUrlShortenerRepository;
import com.log1995.urlshortener.infrastructure.MemoryUrlShortenerRepository;
import com.log1995.urlshortener.presentation.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import static org.assertj.core.api.Assertions.assertThat;
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
         when(urlShortenerRepository.findOriginUrlInUser(any())).thenReturn(new User(ORIGIN_URL, "1q2w3e4r", 0));
         String originUrl = urlShortenerService.findOriginUrl("ddd");

        // then
         assertThat(userDto.getOriginUrl()).isEqualTo(originUrl);
    }

//    public User(String originUrl, String changedUrl, int responseTime) {
//        this.originUrl = originUrl;
//        this.changedUrl = changedUrl;
//        this.responseTime = responseTime;
//    }

//    @Test
//    void 원본URL을_단축하고_조회하면_원본URL이_조회된다() {
//        UserDto userDto = new UserDto();
//        userDto.setOriginUrl("https://www.naver.com");
//
//        urlShortenerService.changeUrl(userDto);
//        // 변경 + 저장
//
////        String changedUrl = urlShortenerService.findChangedUrl(userDto.getOriginUrl());
//
//        String originUrl = urlShortenerService.findOriginUrl(userDto.getChangedUrl());
//        assertThat(userDto.getOriginUrl()).isEqualTo(originUrl);
//    }

}
