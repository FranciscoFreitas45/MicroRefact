package com.example.demo.controller;
 import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.Dao.CategoryDAO;
import com.example.demo.Dao.ProductDAO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.Users;
import com.example.demo.service.ProductService;
import com.example.demo.service.SessionService;
@Controller
@RequestMapping("/home")
public class HomeController {

@Autowired
 private CategoryDAO category;

@Autowired
 private ProductService productService;

@Autowired
 private SessionService session;


@RequestMapping("/category/{categoryid}")
public String findByCategory(Model model,String categoryid,Optional<Integer> p){
    Category cate = new Category();
    model.addAttribute("cate", cate);
    List<Category> cates = category.findAll();
    model.addAttribute("cates", cates);
    // 
    Pageable pageable = PageRequest.of(p.orElse(0), 9);
    Page<Product> page = productService.findByCategoryId(categoryid, pageable);
    model.addAttribute("page", page);
    model.addAttribute("categoryid", categoryid);
    model.addAttribute("view", "user/productfindcategoryid.jsp");
    return "/home";
}


@RequestMapping("")
public String page(Model model,Optional<Integer> p){
    Category cate = new Category();
    model.addAttribute("cate", cate);
    List<Category> cates = category.findAll();
    model.addAttribute("cates", cates);
    // 
    Pageable pageable = PageRequest.of(p.orElse(0), 9);
    Page<Product> page = productService.findAll(pageable);
    model.addAttribute("page", page);
    model.addAttribute("view", "user/product.jsp");
    // 
    Users user = session.get("user");
    String login = "";
    if (user == null) {
        login = "Login";
    } else {
        login = "log out";
    }
    model.addAttribute("login", login);
    return "home";
}


public List<Category> category(){
    List<Category> items = category.findAll();
    return items;
}


}