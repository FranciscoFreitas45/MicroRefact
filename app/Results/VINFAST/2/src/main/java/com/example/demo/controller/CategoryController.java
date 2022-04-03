package com.example.demo.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.Dao.CategoryDAO;
import com.example.demo.entity.Category;
@Controller
@RequestMapping("/admin/category")
public class CategoryController {

@Autowired
 private CategoryDAO dao;


@RequestMapping("/edit/{id}")
public String edit(Model model,String id){
    Category item = dao.findById(id).get();
    model.addAttribute("item", item);
    List<Category> items = dao.findAll();
    model.addAttribute("items", items);
    model.addAttribute("view", "/views/admin/category/index.jsp");
    return "/layout";
}


@RequestMapping("/index")
public String index(Model model){
    Category item = new Category();
    model.addAttribute("item", item);
    List<Category> items = dao.findAll();
    model.addAttribute("items", items);
    model.addAttribute("view", "/views/admin/category/index.jsp");
    return "/layout";
}


@RequestMapping("/create")
public String create(Category item,Model model,Errors error){
    if (error.hasErrors()) {
        model.addAttribute("message", "du lieu khong hop le");
    } else {
        model.addAttribute("message", "du lieu hop le");
        dao.save(item);
    }
    return "redirect:/admin/category/index";
}


@RequestMapping("/update")
public String update(Category item){
    dao.save(item);
    return "redirect:/admin/category/edit/" + item.getId();
}


@RequestMapping("/delete/{id}")
public String delete(String id){
    dao.deleteById(id);
    return "redirect:/admin/category/index";
}


}