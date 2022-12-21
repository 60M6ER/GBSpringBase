package ru.gb.springapp.services;

import org.springframework.stereotype.Service;
import ru.gb.springapp.models.Product;
import ru.gb.springapp.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Object findById(Long id) {
        if (id.equals(-1L))
            return new Product();
        else
            return productRepository.getById(id);
    }

    public void deleteProduct(Long id) {
        productRepository.removeById(id);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
