package com.log1995.urlshortener.presentation;

import com.log1995.urlshortener.domain.ShortenUrl;

public class ShortenUrlResponseDto {

    private String originUrl;
    private String changedUrl;
    private int viewCount;

    public ShortenUrlResponseDto() {
        this.viewCount = 0;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getChangedUrl() {
        return changedUrl;
    }

    public void setChangedUrl(String changedUrl) {
        this.changedUrl = changedUrl;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    // DTO -> Entity
    public ShortenUrl dtoToEntity() {
        return new ShortenUrl(this.originUrl, this.changedUrl, this.viewCount);
    }

    // Entity -> DTO
    public ShortenUrlResponseDto(ShortenUrl shortenUrl) {
        this.originUrl = shortenUrl.getOriginUrl();
        this.changedUrl = shortenUrl.getChangedUrl();
        this.viewCount = shortenUrl.getViewCount();
    }


}
