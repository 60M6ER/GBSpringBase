package ru.gb.springdata.dto;

import lombok.Data;
import ru.gb.springdata.model.Product;

@Data
public class ProductDto {

    private Long id;
    private String title;
    private Double price;

    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}
