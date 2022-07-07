package com.log1995.urlshortener.domain;

public class User {

//    public User() {
//
//    }

    public User(String originUrl, String changedUrl, int responseTime) {
        this.originUrl = originUrl;
        this.changedUrl = changedUrl;
        this.responseTime = responseTime;
    }

    private String originUrl;
    private String changedUrl;
    private int responseTime;

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
}
