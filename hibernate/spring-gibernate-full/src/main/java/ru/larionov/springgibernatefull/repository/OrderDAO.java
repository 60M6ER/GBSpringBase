package ru.larionov.springgibernatefull.repository;

import ru.larionov.springgibernatefull.model.Consumer;
import ru.larionov.springgibernatefull.model.Order;
import ru.larionov.springgibernatefull.model.Product;

public interface OrderDAO {

    Order getOrderByProductAndConsumer(Product product, Consumer consumer);
}
