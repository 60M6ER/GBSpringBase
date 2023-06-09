package ru.gb.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.repository.ProductRepository;

@Service
public class InitService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //@EventListener(ApplicationReadyEvent.class)
    public void initProducts() {
        productRepository.deleteAll();
        for (int i = 1; i < 21; i++) {
            productRepository.save(
                    new Product("Product " + i,
                    Double.parseDouble(String.format("%.2f", 1 + Math.random() * 99).replace(",", "."))
                    )
            );
        }
    }
}
