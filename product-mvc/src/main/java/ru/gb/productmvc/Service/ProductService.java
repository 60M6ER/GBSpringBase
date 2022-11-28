package ru.gb.productmvc.Service;

import org.springframework.stereotype.Service;
import ru.gb.productmvc.model.Product;
import ru.gb.productmvc.repository.ProductRepo;

import java.util.List;

@Service
public class ProductService {

    private ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getById(Long id) {
        if (id == -1) return new Product();
        return productRepo.findByID(id);
    }

    public void delProduct(Long id){
        productRepo.delete(id);
    }

    public void saveProduct(Product product) {
        productRepo.save(product);
    }
}
