package ru.larionov.springgibernatefull.repository;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import ru.larionov.springgibernatefull.model.Consumer;
import ru.larionov.springgibernatefull.model.Product;
import ru.larionov.springgibernatefull.service.SessionFactoryUtils;

import java.util.List;

public interface ConsumerDAO {

    Consumer getById(Long id);

    List<Consumer> getConsumersByProduct(Product product);
}
