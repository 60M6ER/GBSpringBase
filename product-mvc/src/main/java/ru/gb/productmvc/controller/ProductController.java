package ru.gb.productmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.productmvc.Service.ProductService;
import ru.gb.productmvc.model.Product;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "list_products";
    }

    @GetMapping("/show/{id}")
    public String showProduct(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.getById(id));
        return "product";
    }

    @GetMapping("/del/{id}")
    public String delProduct(Model model, @PathVariable Long id) {
        productService.delProduct(id);
        return "redirect:/";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }
}
