package nvc.bcit.soi5shopstart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import nvc.bcit.soi5shopstart.model.Category;
import nvc.bcit.soi5shopstart.model.Product;
import nvc.bcit.soi5shopstart.repository.CategoryRepository;
import nvc.bcit.soi5shopstart.service.ProductService;

@Controller
public class MainController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

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

    @GetMapping("/category/product")
    public ModelAndView getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return new ModelAndView("category", "categories", categories);
    }

    @GetMapping("/product/name/{name}")   //path variable
    public ModelAndView getProductsByName (@PathVariable("name") String name) {
        List<Product> products = productService.findByName(name);
        return new ModelAndView("product", "products", products);
    }

    @GetMapping("/product/price/{price}")   //path variable
    public ModelAndView getProductsByPrice (@PathVariable("price") double price) {
        List<Product> products = productService.findByPrice(price);
        return new ModelAndView("product", "products", products);
    }

    //  /product/stock/50
    @GetMapping("/product/stock/{unit}")   //path variable
    public ModelAndView getProductsByUnitInStock (@PathVariable("unit") int unit) {
        List<Product> products = productService.findByUnitInStock(unit);
        return new ModelAndView("product", "products", products);
    }
}
