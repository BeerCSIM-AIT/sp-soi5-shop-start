package nvc.bcit.soi5shopstart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nvc.bcit.soi5shopstart.model.Category;
import nvc.bcit.soi5shopstart.model.Product;
import nvc.bcit.soi5shopstart.repository.CategoryRepository;
import nvc.bcit.soi5shopstart.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    public ModelAndView product () {
        List<Product> products = productService.findAll();
        return new ModelAndView("product", "products", products);
    }

    @GetMapping("/new")
    public String newProduct (ModelMap modelMap) {
        Product product = new Product();
        modelMap.addAttribute("product", product);
        return "newproduct";
    }

    @GetMapping("/edit")
    public String editProduct () {
        return "editproduct";
    }
    @GetMapping("/name/{name}")   //path variable
    public ModelAndView getProductsByName (@PathVariable("name") String name) {
        List<Product> products = productService.findByName(name);
        return new ModelAndView("product", "products", products);
    }

    @PostMapping("/name")
    public ModelAndView searchProductsByName (String name, ModelMap modelMap) {
        List<Product> products = productService.findByName(name);
        modelMap.addAttribute("name", name);
        return new ModelAndView("product", "products", products);
    }

    @GetMapping("/price/{price}")   //path variable
    public ModelAndView getProductsByPrice (@PathVariable("price") double price) {
        List<Product> products = productService.findByPrice(price);
        return new ModelAndView("product", "products", products);
    }

    //  /product/stock/50
    @GetMapping("/stock/{unit}")   //path variable
    public ModelAndView getProductsByUnitInStock (@PathVariable("unit") int unit) {
        List<Product> products = productService.findByUnitInStock(unit);
        return new ModelAndView("product", "products", products);
    }

    @PostMapping("/add")
    public String saveProduct(Product product, BindingResult result){
        // do save()
        if(result.hasErrors()){
            return "newproduct";
        }else{
            // binding OK
            productService.save(product);
        }
        return "redirect:/product"; 
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product prd, BindingResult result){
        if(result.hasErrors()){
            return "editproduct";
        }else{
            // binding OK
            // ดึง product เดิมมาก่อน (ดึงตาม id)
            Product product = productService.getById(prd.getId());
            product.setName(prd.getName());
            product.setPrice(prd.getPrice());
            product.setUnitInStock(prd.getUnitInStock());
            product.setCategory(prd.getCategory());
            productService.save(product);
            return "redirect:/product";
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable int id){
        // ดึง product ที่ต้องการลบ (ตาม id ที่ระบุ)
        Product product = productService.getById(id);
        productService.delete(product);
        return new ModelAndView("redirect:/product");
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable int id, ModelMap modelMap){
        Product product = productService.getById(id);
        modelMap.addAttribute("product", product);
        return "editproduct";
    }

    // เพิ่ม attribute categories เข้าไปในโมเดล เวลาเรียก Request ทุกอัน
    @ModelAttribute("categories")
    public List<Category> loadCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
