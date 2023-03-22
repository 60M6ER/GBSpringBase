package ru.gb.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.gb.springdata.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByPriceGreaterThanEqual(Double price);
    List<Product> findAllByPriceLessThanEqual(Double price);
    List<Product> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Double minPrice, Double maxPrice);


}
