package ru.gb.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.springdata.dto.Answer;
import ru.gb.springdata.dto.ProductDto;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.repository.ProductRepository;
import ru.gb.springdata.specification.ProductSpecifications;

import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductDto> findProducts(Double minPrice, Double maxPrice, Integer page, String sortColumn, Boolean sortASC) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null)
            spec = spec.and(ProductSpecifications.priceGreaterOrEqual(minPrice));
        if (maxPrice != null)
            spec = spec.and(ProductSpecifications.priceLessOrEqual(maxPrice));

        return productRepository.findAll(
                spec,
                PageRequest.of(
                        page - 1,
                        5,
                        sortASC ? Sort.by(sortColumn) : Sort.by(sortColumn).descending()
                )
        )
                .map(ProductDto::new);
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

    public ProductDto findById(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        } else {
            return new ProductDto(byId.get());
        }
    }

    public ProductDto createNew(ProductDto productDto) {
        //productDto.setId(null);
        return new ProductDto(
                productRepository.save(
                        new Product(productDto)
                )
        );
    }
}
