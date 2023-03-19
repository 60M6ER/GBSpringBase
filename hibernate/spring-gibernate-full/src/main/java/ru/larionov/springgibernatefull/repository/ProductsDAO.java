package ru.larionov.springgibernatefull.repository;

import ru.larionov.springgibernatefull.model.Consumer;
import ru.larionov.springgibernatefull.model.Product;

import java.util.List;

public interface ProductsDAO {

    Product getById(Long id);
    List<Product> getProductsByConsumer(Consumer consumer);
}
