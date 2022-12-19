package ru.gb.productmvc.repository;

import ru.gb.productmvc.model.Product;

import java.util.List;

public interface ProductDAO {

    Product findById(Long id);

    List<Product> findAll();
    void delete(Long id);
    void save(Product product);

}
