package com.log1995.urlshortener.infrastructure;

import com.log1995.urlshortener.domain.UrlShortenerRepository;
import com.log1995.urlshortener.domain.ShortenUrlInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Profile({"prod", "dev"})
@Repository
public class DataBaseUrlShortenerRepository implements UrlShortenerRepository {

    private final EntityManager em;

    @Autowired
    public DataBaseUrlShortenerRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void saveShortenUrlInfo(ShortenUrlInfo shortenUrlInfo) {
        em.persist(shortenUrlInfo);
    }

    @Override
    public List<ShortenUrlInfo> findShortenUrlInfoByChangedUrl(String url) {
        List<ShortenUrlInfo> resultList =  em.createQuery("select s from ShortenUrl s where s.changedUrl = :url", ShortenUrlInfo.class)
                .setParameter("url", url)
                .getResultList();

        return resultList;
    }
}
