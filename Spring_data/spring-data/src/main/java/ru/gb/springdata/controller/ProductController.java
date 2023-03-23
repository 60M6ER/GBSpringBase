package ru.gb.springdata.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdata.dto.Answer;
import ru.gb.springdata.dto.ProductDto;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.repository.ProductRepository;
import ru.gb.springdata.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductRepository(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping
    public Page<ProductDto> showAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "sortColumn", defaultValue = "title") String sortColumn,
            @RequestParam(name = "sortASC", defaultValue = "true") Boolean sortASC,
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice) {
        if (page < 1) page = 1;

        return productService.findProducts(minPrice, maxPrice, page, sortColumn, sortASC);
    }

    @DeleteMapping("/{id}")
    public Answer deleteById(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto product) {
        return productService.createNew(product);
    }
}
