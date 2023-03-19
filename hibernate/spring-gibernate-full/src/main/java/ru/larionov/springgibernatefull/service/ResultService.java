package ru.larionov.springgibernatefull.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.larionov.springgibernatefull.model.Consumer;
import ru.larionov.springgibernatefull.model.Order;
import ru.larionov.springgibernatefull.model.Product;
import ru.larionov.springgibernatefull.repository.ConsumerDAO;
import ru.larionov.springgibernatefull.repository.OrderDAO;
import ru.larionov.springgibernatefull.repository.ProductsDAO;

import java.util.List;

@Service
public class ResultService {

    ProductsDAO productsDAO;
    ConsumerDAO consumerDAO;

    OrderDAO orderDAO;

    @Autowired
    public void setProductsDAO(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @Autowired
    public void setConsumerDAO(ConsumerDAO consumerDAO) {
        this.consumerDAO = consumerDAO;
    }

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Product> showAllProductsByConsumer(Consumer consumer) {
        return productsDAO.getProductsByConsumer(consumer);
    }

    public List<Consumer> showAllConsumersByProduct(Product product) {
        return consumerDAO.getConsumersByProduct(product);
    }

    @PostConstruct
    public void resultTest() {
        Consumer consumer = consumerDAO.getById(1L);
        Product product = productsDAO.getById(1L);

        System.out.println("The consumer - [" + consumer.getName() + "] bought next products:");
        List<Product> products = showAllProductsByConsumer(consumer);

        products.forEach(p -> {
            Order order = orderDAO.getOrderByProductAndConsumer(p, consumer);
            System.out.println("Bought [" + p.getTitle() + "] for [" + order.getPrice() + "] units");
        });

        List<Consumer> consumers = showAllConsumersByProduct(product);

        System.out.println("Next consumers bought this product - [" + product.getTitle() + "] :");
        consumers.forEach(c -> {
            Order order = orderDAO.getOrderByProductAndConsumer(product, c);
            System.out.println("[" + c.getName() + "] bought for [" + order.getPrice() + "] units");
        });

    }
}
