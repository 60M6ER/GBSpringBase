package ru.gb.springdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).get();
    }

    @GetMapping(value = {"/", "/filtermin/{minPrice}", "/filtermax/{maxPrice}", "/filterminmax/{minPrice}/{maxPrice}"})
    public List<Product> showAllProducts(@PathVariable(required = false) Double minPrice, @PathVariable(required = false) Double maxPrice) {
        if (maxPrice != null && minPrice != null)
            return productRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice);
        else if (minPrice != null)
            return productRepository.findAllByPriceGreaterThanEqual(minPrice);
        else if (maxPrice != null)
            return productRepository.findAllByPriceLessThanEqual(maxPrice);
        else
            return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
}
