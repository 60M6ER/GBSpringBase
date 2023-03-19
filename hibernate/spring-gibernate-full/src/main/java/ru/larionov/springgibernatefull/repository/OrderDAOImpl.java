package ru.larionov.springgibernatefull.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.larionov.springgibernatefull.model.Consumer;
import ru.larionov.springgibernatefull.model.Order;
import ru.larionov.springgibernatefull.model.Product;
import ru.larionov.springgibernatefull.service.SessionFactoryUtils;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO{

    SessionFactoryUtils factoryUtils;

    @Autowired
    public void setFactoryUtils(SessionFactoryUtils factoryUtils) {
        this.factoryUtils = factoryUtils;
    }

    @Override
    public Order getOrderByProductAndConsumer(Product product, Consumer consumer) {
        try (Session session = factoryUtils.getSession()) {
            session.beginTransaction();
            Query<Order> query = session.createQuery("select o from Order o where o.product = :product and o.consumer = :consumer")
                    .setParameter("product", product)
                    .setParameter("consumer", consumer);
            List<Order> orders = query.getResultList();
            session.getTransaction().commit();
            if (orders.size() > 0)
                return orders.get(0);
            else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
