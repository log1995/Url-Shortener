package com.log1995.urlshortener.domain;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class ShortenUrlInfo {

    public ShortenUrlInfo() {

    }

    public ShortenUrlInfo(String originUrl, String changedUrl, int viewCount) {
        this.originUrl = originUrl;
        this.changedUrl = changedUrl;
        this.viewCount = viewCount;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORIGINURL")
    private String originUrl;

    @Column(name = "CHANGEDURL")
    private String changedUrl;

    @Column(name = "RESPONSETIME")
    private Integer viewCount;

    public String getOriginUrl() {
        return originUrl;
    }

    public String getChangedUrl() {
        return changedUrl;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void increaseViewCount() {
        this.viewCount = this.viewCount + 1;
    }
}
