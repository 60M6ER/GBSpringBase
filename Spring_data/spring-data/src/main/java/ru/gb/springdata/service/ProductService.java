package ru.gb.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.springdata.dto.Answer;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.repository.ProductRepository;
import ru.gb.springdata.specification.ProductSpecifications;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findProducts(Double minPrice, Double maxPrice, Integer page, String sortColumn, Boolean sortASC) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null)
            spec = spec.and(ProductSpecifications.priceGreaterOrEqual(minPrice));
        if (maxPrice != null)
            spec = spec.and(ProductSpecifications.priceLessOrEqual(maxPrice));

        return productRepository.findAll(spec, PageRequest.of(page - 1, 5, sortASC ? Sort.by(sortColumn) : Sort.by(sortColumn).descending()));
    }

    public Answer deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return new Answer(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Answer(false);
        }

    }
}
