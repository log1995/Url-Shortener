package com.log1995.urlshortener.infrastructure;

import com.log1995.urlshortener.domain.UrlShortenerRepository;
import com.log1995.urlshortener.domain.User;
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
    public void saveUrl(User user) {
        em.persist(user);
    }

    @Override
    public User findOriginUrlInUser(String url) {
        List<User> resultList =  em.createQuery("select u from User u where u.changedUrl = :url", User.class)
                .setParameter("url", url)
                .getResultList();
        User user = resultList.get(0);
        user.setResponseTime(user.getResponseTime() + 1);
        return user;
    }

    @Override
    public User findResponseCountInUser(String url) {
        List<User> resultList =  em.createQuery("select u from User u where u.changedUrl = :url", User.class)
                .setParameter("url", url)
                .getResultList();
        User user = resultList.get(0);

        return user;
    }

    @Override
    public User findChangedUrlInUser(String originUrl) {
        List<User> resultList =  em.createQuery("select u from User u where u.originUrl = :url", User.class)
                .setParameter("url", originUrl)
                .getResultList();
        User user = resultList.get(0);

        return user;
    }

}
