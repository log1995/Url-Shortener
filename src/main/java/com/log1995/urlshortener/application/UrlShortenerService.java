package com.log1995.urlshortener.application;

import com.log1995.urlshortener.exception.CannotFoundUrlException;
import com.log1995.urlshortener.exception.TryAgainException;
import com.log1995.urlshortener.presentation.ShortenUrlResponseDto;
import com.log1995.urlshortener.domain.ShortenUrlInfo;
import com.log1995.urlshortener.exception.InValidUrlException;
import com.log1995.urlshortener.domain.UrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Transactional
@Service
public class UrlShortenerService {

    @Autowired
    private final UrlShortenerRepository urlShortenerRepository;

    private static final String REGEX = "^((http|https)://)?(www\\.)?[a-zA-Z0-9]+\\.[a-z]+(\\.[a-z]+)?";
    private static final int MAX_TRY = 10;
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

    public void checkValidationOfUrl(String url) {
        if(Pattern.matches(REGEX, url)) {
           return;
        }
        throw new InValidUrlException();
    }

    public String makeRandomUrl() {
        int tryCount = 0;

        while(tryCount < MAX_TRY) {
            Random random = new Random();
            String randomString = random.ints(START, END + 1)
                    .filter(i -> isRightRange(i))
                    .limit(LIMIT)
                    .collect(StringBuffer::new, StringBuffer::appendCodePoint, StringBuffer::append)
                    .toString();

            if(checkSameChangedUrlInRepository(randomString)) {
                return randomString;
            }

            tryCount++;
        }
        throw new TryAgainException();
    }

    private boolean isRightRange(int i) {
        return (i <= NUM_NINE || i >= ALPHABET_A) && (i <= ALPHABET_Z || i >= ALPHABET_a);
    }

    private boolean checkSameChangedUrlInRepository(String radomString) {
        List<ShortenUrlInfo> shortenUrlInfoList = urlShortenerRepository.findShortenUrlInfoByChangedUrl(radomString);
        if(shortenUrlInfoList.isEmpty()) {
            return true;
        }
        return false;
    }

    public void saveUrlInfo(ShortenUrlResponseDto shortenUrlResponseDTO) {
        ShortenUrlInfo shortenUrlInfo = shortenUrlResponseDTO.dtoToEntity();
        urlShortenerRepository.saveShortenUrlInfo(shortenUrlInfo);
    }

    public String findOriginUrl(String changedUrl) {
        List<ShortenUrlInfo> shortenUrlInfoList = urlShortenerRepository.findShortenUrlInfoByChangedUrl(changedUrl); // 1. shortenUrl 조회
        if(shortenUrlInfoList.isEmpty()) {
            throw new CannotFoundUrlException();
        }
        ShortenUrlInfo shortenUrlInfo = shortenUrlInfoList.get(0);

        shortenUrlInfo.increaseViewCount();                // 2. shortenUrl에게 행동을 시킴(응답횟수 증가)
        urlShortenerRepository.saveShortenUrlInfo(shortenUrlInfo);       // 3. shortenUrl를 저장

        return shortenUrlInfo.getOriginUrl();              // 4. shortenUrl 반환
    }

    public int findViewCount(String changedUrl) {
        String[] uri = changedUrl.split("/");
        List<ShortenUrlInfo> shortenUrlInfoList = urlShortenerRepository.findShortenUrlInfoByChangedUrl(uri[1]);
        if(shortenUrlInfoList.isEmpty()) {
            throw new CannotFoundUrlException();
        }
        ShortenUrlInfo shortenUrlInfo = shortenUrlInfoList.get(0);

        return shortenUrlInfo.getViewCount();
    }

}
