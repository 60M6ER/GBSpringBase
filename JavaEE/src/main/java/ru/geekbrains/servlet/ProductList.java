package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

@WebServlet(name = "ProductServlet", urlPatterns = "/product_list")
public class ProductList extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(ProductList.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("New GET request");
        Product[] products = new Product[10];

        for (int i = 0; i < products.length; i++) {
            products[i] = new Product(
                    i,
                    "Product " + (i + 1),
                    (Math.random() * 99.0) + 1
                    );
        }

        resp.getWriter().printf("<h1>Product list:</h1>");
        resp.getWriter().printf("<table style='border: 2px solid'>");
        resp.getWriter().printf("<tr><td style='border: 2px solid'>Id</td><td style='border: 2px solid'>Title</td><td style='border: 2px solid'>Cost</td></tr>");
        for (int i = 0; i < products.length; i++) {
            resp.getWriter().printf(String.format("<tr><td style='border: 2px solid'>%s</td><td style='border: 2px solid'>%s</td><td style='border: 2px solid'>%.2f</td></tr>",
                    products[i].getId(),
                    products[i].getTitle(),
                    products[i].getCost()));
        }
        resp.getWriter().printf("</table>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("New POST request");
        resp.getWriter().printf("<h1>New POST request</h1>");
    }
}
