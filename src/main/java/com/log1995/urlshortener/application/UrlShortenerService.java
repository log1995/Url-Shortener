package com.log1995.urlshortener.application;

import com.log1995.urlshortener.presentation.ShortenUrlResponseDto;
import com.log1995.urlshortener.domain.ShortenUrl;
import com.log1995.urlshortener.exception.NotContainHttpInUrlException;
import com.log1995.urlshortener.domain.UrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Transactional
@Service
public class UrlShortenerService {

    private final UrlShortenerRepository urlShortenerRepository;

    private final String localHost = "localhost:8080/";
    private static final int START = 48;
    private static final int END = 122;
    private static final int LIMIT = 10;

    private static final int NUM_NINE = 57;
    private static final int ALPHABET_A = 65;
    private static final int ALPHABET_Z = 90;
    private static final int ALPHABET_a = 97;

    @Autowired
    public UrlShortenerService(UrlShortenerRepository urlShortenerRepository) {
        this.urlShortenerRepository = urlShortenerRepository;
    }

    public void checkHttpContainInUrl(String url) {
        if(url.indexOf("http") == -1) {
            throw new NotContainHttpInUrlException();
        }
    }

    private String checkSameChangedUrlInRepository() {
        boolean existSameChangedUrl;
        String randomUrl;

        do {
           randomUrl = makeRandomUrl();
           existSameChangedUrl = urlShortenerRepository.findSameChangedUrlInRepository(randomUrl);
        } while(existSameChangedUrl);

        return randomUrl;

    }


//    memberRepository.findByName(member.getName())
//            .ifPresent(m -> {
//        throw new IllegalStateException("이미 존재하는 회원입니다.");
//    });

    public String changeUrl(ShortenUrlResponseDto shortenUrlResponseDTO) {
        String randomUrl = checkSameChangedUrlInRepository();

        shortenUrlResponseDTO.setChangedUrl(randomUrl);
        ShortenUrl shortenUrl = shortenUrlResponseDTO.dtoToEntity();
        saveUrl(shortenUrl);

        return localHost + shortenUrlResponseDTO.getChangedUrl();
    }

    private String makeRandomUrl() {
        Random random = new Random();
        String randomString = random.ints(START, END + 1)
                .filter(i -> isRightRange(i))
                .limit(LIMIT)
                .collect(StringBuffer::new, StringBuffer::appendCodePoint, StringBuffer::append)
                .toString();

        return randomString;
    }

    private boolean isRightRange(int i) {
        return (i <= NUM_NINE || i >= ALPHABET_A) && (i <= ALPHABET_Z || i >= ALPHABET_a);
    }

    private void saveUrl(ShortenUrl shortenUrl) {
        urlShortenerRepository.saveUrl(shortenUrl);
    }

    public String findOriginUrl(String changedUrl) {
        ShortenUrl shortenUrl = urlShortenerRepository.findUserByChangedUrl(changedUrl); // 1. shortenUrl 조회

        shortenUrl.increaseViewCount();                // 2. shortenUrl에게 행동을 시킴(응답횟수 증가)
        urlShortenerRepository.saveUrl(shortenUrl);       // 3. shortenUrl를 저장

        return shortenUrl.getOriginUrl();              // 4. shortenUrl 반환
    }

    public int findViewCount(String changedUrl) {
        String[] uri = changedUrl.split("/");
        ShortenUrl shortenUrl = urlShortenerRepository.findUserByChangedUrl(uri[1]);

        return shortenUrl.getViewCount();
    }

}
