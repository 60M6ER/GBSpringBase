package ru.gb.springdata.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.gb.springdata.dto.ProductDto;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private double price;

    public Product() {
    }

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public Product(ProductDto productDto){
        this.id = productDto.getId();
        this.title = productDto.getTitle();
        this.price = productDto.getPrice();
    }
}
