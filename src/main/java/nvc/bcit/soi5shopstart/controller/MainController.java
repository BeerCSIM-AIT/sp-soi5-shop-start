package nvc.bcit.soi5shopstart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import nvc.bcit.soi5shopstart.model.Product;
import nvc.bcit.soi5shopstart.service.ProductService;

@Controller
public class MainController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String index () {
        return "index";
    }
    
    @GetMapping("/product")
    public ModelAndView product () {
        List<Product> products = productService.findAll();
        return new ModelAndView("product", "products", products);
    }

    @GetMapping("/product/new")
    public String newProduct () {
        return "newproduct";
    }

    @GetMapping("/product/edit")
    public String editProduct () {
        return "editproduct";
    }
}
