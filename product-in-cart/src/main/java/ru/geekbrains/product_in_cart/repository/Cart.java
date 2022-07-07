package ru.geekbrains.product_in_cart.repository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.geekbrains.product_in_cart.models.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Cart {

    private Map<Product, Integer> products;

    public Cart() {
        products = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            Integer quantity = products.get(product);
            quantity = quantity + 1;
            products.put(product, quantity);
        } else {
            products.put(product, 1);
        }
    }

    public void delProduct(Product product) {
        if (products.containsKey(product)) {
            Integer quantity = products.get(product);
            if (quantity > 1) {
                quantity = quantity - 1;
                products.put(product, quantity);
            } else {
                products.remove(product);
            }

        }else {
            System.out.println(String.format("Продукта %s нет", product.getTitle()));
        }
    }

    public void showCart() {
        if (products.size() == 0)
            System.out.println("Корзина пуста");
        AtomicReference<Double> result = new AtomicReference<>(0d);
        products.entrySet().stream().forEach(e -> {
            System.out.println(String.format("id: %d, Название: %s, Цена: %.2f р., Количество: %d, Итого: %.2f р.",
                    e.getKey().getId(),
                    e.getKey().getTitle(),
                    e.getKey().getCost(),
                    e.getValue(),
                    e.getKey().getCost() * e.getValue()));
            result.updateAndGet(v -> v + e.getKey().getCost() * e.getValue());
                }
        );
        System.out.println(String.format("Итого по корзине: %.2f р.", result.get()));
    }
}
