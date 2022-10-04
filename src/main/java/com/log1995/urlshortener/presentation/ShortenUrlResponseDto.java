package com.log1995.urlshortener.presentation;

import com.log1995.urlshortener.domain.ShortenUrlInfo;

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
    public ShortenUrlInfo dtoToEntity() {
        return new ShortenUrlInfo(this.originUrl, this.changedUrl, this.viewCount);
    }

    // Entity -> DTO
    public ShortenUrlResponseDto(ShortenUrlInfo shortenUrlInfo) {
        this.originUrl = shortenUrlInfo.getOriginUrl();
        this.changedUrl = shortenUrlInfo.getChangedUrl();
        this.viewCount = shortenUrlInfo.getViewCount();
    }


}
