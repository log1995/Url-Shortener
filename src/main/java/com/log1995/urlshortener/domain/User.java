package com.log1995.urlshortener.domain;

import javax.persistence.*;

@Entity
public class User {

    public User() {

    }

    public User(String originUrl, String changedUrl, int responseTime) {
        this.originUrl = originUrl;
        this.changedUrl = changedUrl;
        this.responseTime = responseTime;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORIGINURL")
    private String originUrl;

    @Column(name = "CHANGEDURL")
    private String changedUrl;

    @Column(name = "RESPONSETIME")
    private Integer responseTime;

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
