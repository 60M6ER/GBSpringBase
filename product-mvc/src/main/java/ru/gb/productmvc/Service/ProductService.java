package ru.gb.productmvc.Service;

import org.springframework.stereotype.Service;
import ru.gb.productmvc.model.Product;
import ru.gb.productmvc.repository.ProductDAO;

import java.util.List;

@Service
public class ProductService {

    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public Product getById(Long id) {
        if (id == -1) return new Product();
        return productDAO.findByID(id);
    }

    public void delProduct(Long id){
        productDAO.delete(id);
    }

    public void saveProduct(Product product) {
        productDAO.save(product);
    }
}
