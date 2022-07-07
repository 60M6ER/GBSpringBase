package ru.geekbrains.product_in_cart.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.product_in_cart.models.Product;
import ru.geekbrains.product_in_cart.repository.Cart;
import ru.geekbrains.product_in_cart.repository.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ConsoleInterface {

    private ProductRepository productRepository;
    private CartFactory cartFactory;

    private Cart cart;

    public ConsoleInterface(ProductRepository productRepository, CartFactory cartFactory) {
        this.productRepository = productRepository;
        this.cartFactory = cartFactory;
    }

    @PostConstruct
    public void startInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для начала работы необходимо ввести команду /start . Для выхода /exit");
        boolean running = true;
        while (running) {
            String s = scanner.nextLine();
            if (s.equals("/exit")) {
                running = false;
                System.out.println("Досвидания!");
            } else if (s.equals("/start")) {
                startLogic();
            } else if (s.equals("/showProductList")) {
                showProducts(productRepository.findAll());
            } else if (s.equals("/showCart")) {
                showCart();
            } else if (s.equals("/end")) {
                endLogic();
            } else {
                String[] commands = s.split(" ");
                if (commands[0].equals("/addCart")) {
                    if (commands.length == 2)
                        addCart(commands[1]);
                } else if (commands[0].equals("/delCart")) {
                    if (commands.length == 2)
                        delCart(commands[1]);
                }
            }
        }
    }

    private void startLogic() {
        if (cart != null) {
            System.out.println("Есть активная корзина. Для завершения работы с ней введите /end");
            return;
        }
        cart = cartFactory.getCart();
        System.out.println("Все продукты:");
        showProducts(productRepository.findAll());
        System.out.println("/showCart - показывает состав корзины.");
        System.out.println("/end - удаляет корзину.");
        System.out.println("/showProductList - показывает имеющиеся продукты.");
        System.out.println("/addCart 1 - добавляет в корзину продукт с идентификатором 1.");
        System.out.println("/delCart 1 - удаляет из корзины продукт с идентификатором 1.");
        System.out.println("/exit - заверщает работу.");
    }

    private void endLogic() {
        cart = null;
        System.out.println("Корзина удалена!");
    }

    private void showCart() {
        if (!testCart()) return;
        cart.showCart();
    }

    private void addCart(String id) {
        if (!testCart()) return;
        Optional<Product> product = productRepository.getById(Long.parseLong(id));
        if (product.isEmpty()) {
            System.out.println("Продукт не существует");
        } else {
            cart.addProduct(product.get());
            System.out.println("Продукт добавлен");
        }
    }

    private void delCart(String id) {
        if (!testCart()) return;
        Optional<Product> product = productRepository.getById(Long.getLong(id));
        if (product.isEmpty()) {
            System.out.println("Продукт не существует");
        } else {
            cart.delProduct(product.get());
            System.out.println("Продукт удален");
        }
    }

    private boolean testCart() {
        if (cart == null) {
            System.out.println("Для начала работы с корзиной введите /start");
            return false;
        }
        return true;

    }

    private void showProducts(List<Product> products) {
        products.stream().forEach(product ->
                System.out.println(String.format("id: %d, Название: %s, Цена: %.2f",
                        product.getId(),
                        product.getTitle(),
                        product.getCost())));
    }

}
