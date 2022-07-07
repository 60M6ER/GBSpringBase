package ru.geekbrains.product_in_cart.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ru.geekbrains.product_in_cart.repository.Cart;

@Service
public class CartFactory {

    @Lookup
    public Cart getCart() {
        return null;
    }
}
