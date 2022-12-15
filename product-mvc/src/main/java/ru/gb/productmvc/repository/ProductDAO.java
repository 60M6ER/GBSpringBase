package ru.gb.productmvc.repository;

import org.springframework.stereotype.Repository;
import ru.gb.productmvc.model.Product;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAO {
    private final List<Product> products;

    public ProductDAO() {
        products = new LinkedList<>();

        products.add(new Product(1l, "Product 1", 1.55));
        products.add(new Product(2l, "Product 2", 33.55));
        products.add(new Product(3l, "Product 3", 2.35));
        products.add(new Product(4l, "Product 4", 11.13));
        products.add(new Product(5l, "Product 5", 1.15));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findByID(Long id) {
        Optional<Product> product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return product.isEmpty() ? null : product.get();
    }

    public void delete(Long id){
        products.remove(findByID(id));
    }

    public void save(Product product){
        if (product.getId() != null){
            for (int i = 0; i < products.size(); i++)
                if (products.get(i).getId().equals(product.getId()))
                    products.set(i, product);
        }
        else {
            product.setId(products.stream().map(p -> p.getId()).max(Long::compareTo).get() + 1);
            products.add(product);
        }
    }
}
