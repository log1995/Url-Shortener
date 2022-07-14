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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlShortenerServiceTest {

    @InjectMocks
    private MemoryUrlShortenerRepository memoryUrlShortenerRepository;

    @Mock
    private UrlShortenerService urlShortenerService;

    @Test
    public void save() {
        User user = new User();
        user.setOriginUrl("https://naver.com");
        user.setChangedUrl("q1w2e3r4t5");
        user.setResponseTime(0);

        memoryUrlShortenerRepository.saveUrl(user);


        when(memoryUrlShortenerRepository.findOriginUrlInUser(user.getChangedUrl())).thenReturn(user);

        User user1 = memoryUrlShortenerRepository.findOriginUrlInUser(user.getChangedUrl());
        assertThat(user.getOriginUrl()).isEqualTo(user1.getOriginUrl());
    }

}
