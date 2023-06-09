package ru.gb.springdata.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.gb.springdata.model.Product;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqual(Double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceLessOrEqual(Double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }
}
