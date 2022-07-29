package com.log1995.urlshortener.infrastructure;

import com.log1995.urlshortener.domain.UrlShortenerRepository;
import com.log1995.urlshortener.domain.ShortenUrl;
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
    public void saveUrl(ShortenUrl shortenUrl) {
        em.persist(shortenUrl);
    }

    @Override
    public ShortenUrl findUserByChangedUrl(String url) {
        List<ShortenUrl> resultList =  em.createQuery("select u from User u where u.changedUrl = :url", ShortenUrl.class)
                .setParameter("url", url)
                .getResultList();
        ShortenUrl shortenUrl = resultList.get(0);
        return shortenUrl;
    }

    @Override
    public ShortenUrl findUserByOriginUrl(String originUrl) {
        List<ShortenUrl> resultList =  em.createQuery("select u from User u where u.originUrl = :url", ShortenUrl.class)
                .setParameter("url", originUrl)
                .getResultList();
        ShortenUrl shortenUrl = resultList.get(0);

        return shortenUrl;
    }

    @Override
    public boolean findSameChangedUrlInRepository(String changedUrl) {
        List<ShortenUrl> resultList =  em.createQuery("select u from User u where u.changedUrl = :changedUrl", ShortenUrl.class)
                .setParameter("changedUrl", changedUrl)
                .getResultList();
        if(resultList.get(0) != null) {
            return true;
        }
        return false;
    }
}
