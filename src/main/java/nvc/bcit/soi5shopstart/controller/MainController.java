package nvc.bcit.soi5shopstart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import nvc.bcit.soi5shopstart.model.Category;
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

    @GetMapping("/category/product")
    public ModelAndView getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return new ModelAndView("category", "categories", categories);
    }

    
}
