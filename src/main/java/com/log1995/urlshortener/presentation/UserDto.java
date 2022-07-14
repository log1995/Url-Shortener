package com.log1995.urlshortener.presentation;

import com.log1995.urlshortener.domain.User;

public class UserDto {

    private String originUrl;
    private String changedUrl;
    private int responseTime;

    public UserDto() {
        this.responseTime = 0;
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

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    // DTO -> Entity
    public User dtoToEntity() {
        return new User(this.originUrl, this.changedUrl, this.responseTime);
    }

    // Entity -> DTO
    public UserDto(User user) {
        this.originUrl = user.getOriginUrl();
        this.changedUrl = user.getChangedUrl();
        this.responseTime = user.getResponseTime();
    }


}
