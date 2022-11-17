package ru.geekbrains.product_in_cart.repository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.product_in_cart.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
    }

    public Optional<Product> getById(Long id) {
        return products.stream().filter(p -> p.getId() == id).findFirst();
    }

    public List<Product> findAll() {
        return products;
    }

    @PostConstruct
    public void startInitialize() {
        products.add(new Product(1L, "Продукт 1", 3.5));
        products.add(new Product(2L, "Продукт 2", 5.5));
        products.add(new Product(3L, "Продукт 3", 3.7));
        products.add(new Product(4L, "Продукт 4", 7.5));
        products.add(new Product(5L, "Продукт 5", 9.3));
    }
}
