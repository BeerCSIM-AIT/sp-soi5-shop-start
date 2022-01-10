package nvc.bcit.soi5shopstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index () {
        return "index";
    }
    
    @GetMapping("/product")
    public String product () {
        return "product";
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
