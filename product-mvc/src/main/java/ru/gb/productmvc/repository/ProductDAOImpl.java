package ru.gb.productmvc.repository;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gb.productmvc.service.SessionFactoryService;
import ru.gb.productmvc.model.Product;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private SessionFactoryService factoryService;

    @Autowired
    public void setFactoryService(SessionFactoryService factoryService) {
        this.factoryService = factoryService;
    }


    @Override
    public Product findById(Long id) {
        try (Session session = factoryService.getSession()) {
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
    public List<Product> findAll() {
        try (Session session = factoryService.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
            return products;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = factoryService.getSession()) {
            session.beginTransaction();
            int i = session.createQuery("delete from Product p where p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            System.out.println("Deleted: " + i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void save(Product product) {
        try (Session session = factoryService.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            System.out.println("Saved with id: " + product.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}