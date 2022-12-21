package ru.gb.springapp.repositories;

import org.springframework.stereotype.Repository;
import ru.gb.springapp.models.Product;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    private void init() {
        products = new LinkedList<>();
        products.addAll(Arrays.asList(
                new Product(1L, "Product1", 15.0),
                new Product(2L, "Product2", 400.0),
                new Product(3L, "Product3", 40.0),
                new Product(4L, "Product4", 27.0),
                new Product(5L, "Product5", 150.0)
        ));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product getById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

    public void save(Product product) {
        if (product.getId() == null)
            product.setId(products.stream().map(Product::getId).max(Long::compare).get() + 1);
        Optional<Product> foundedProduct = products.stream().filter(p -> p.getId().equals(product.getId())).findFirst();
        if (foundedProduct.isEmpty()) {
            products.add(product);
        } else {
            foundedProduct.get().update(product);
        }
    }

    public void removeById(Long id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.remove(i);
                break;
            }
        }
    }
}
