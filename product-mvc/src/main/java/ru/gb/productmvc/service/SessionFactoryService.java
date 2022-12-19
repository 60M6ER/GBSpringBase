package ru.gb.productmvc.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class SessionFactoryService {

    private SessionFactory factory;

    @PostConstruct
    public void init() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public Session getSession() {
        return factory.getCurrentSession();
    }

    @PreDestroy
    public void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }
}
