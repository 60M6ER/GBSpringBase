package ru.gb.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springapp.models.Product;
import ru.gb.springapp.repositories.ProductRepository;
import ru.gb.springapp.services.ProductService;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showProductList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "list_products";
    }

    @GetMapping(value = "/show/{id}")
    public String showProductPageById(Model model, @PathVariable Long id){
        model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @GetMapping(value = "/del/{id}")
    public String delProductById(Model model, @PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @PostMapping(value = "/save")
    public String saveProduct(@ModelAttribute Product product){
        productService.saveProduct(product);
        return "redirect:/";
    }
}
