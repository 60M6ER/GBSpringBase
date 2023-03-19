package ru.larionov.springgibernatefull.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.larionov.springgibernatefull.model.Consumer;
import ru.larionov.springgibernatefull.model.Product;
import ru.larionov.springgibernatefull.service.SessionFactoryUtils;

import java.util.List;

@Repository
public class ConsumerDAOImpl implements ConsumerDAO{

    private SessionFactoryUtils factoryUtils;

    @Autowired
    public void setFactoryUtils(SessionFactoryUtils factoryUtils) {
        this.factoryUtils = factoryUtils;
    }

    @Override
    public Consumer getById(Long id) {
        try (Session session = factoryUtils.getSession()) {
            session.beginTransaction();
            Consumer consumer = session.get(Consumer.class, id);
            session.getTransaction().commit();
            return consumer;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Consumer> getConsumersByProduct(Product product) {

        try (Session session = factoryUtils.getSession()) {
            session.beginTransaction();
            Query<Consumer> query = session.createQuery("select o.consumer from Order o where o.product = :product")
                    .setParameter("product", product);
            List<Consumer> consumers = query.getResultList();
            session.getTransaction().commit();
            return consumers;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
