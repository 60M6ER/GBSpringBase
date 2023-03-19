package ru.larionov.springgibernatefull.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.larionov.springgibernatefull.model.Consumer;
import ru.larionov.springgibernatefull.model.Product;
import ru.larionov.springgibernatefull.service.SessionFactoryUtils;

import java.awt.event.PaintEvent;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductsDAO{

    SessionFactoryUtils factoryUtils;

    @Autowired
    public void setFactoryUtils(SessionFactoryUtils factoryUtils) {
        this.factoryUtils = factoryUtils;
    }

    @Override
    public Product getById(Long id) {
        try (Session session = factoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> getProductsByConsumer(Consumer consumer) {
        try (Session session = factoryUtils.getSession()) {
            session.beginTransaction();
            Query<Product> query = session.createQuery("select o.product from Order o where o.consumer = :consumer")
                    .setParameter("consumer", consumer);
            List<Product> products = query.getResultList();
            session.getTransaction().commit();
            return products;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
