package com.fzshuai.controller;
 import com.fzshuai.service.BlogService;
import com.fzshuai.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fzshuai.Interface.TypeService;
@Controller
public class IndexController {

@Autowired
 private  BlogService blogService;

@Autowired
 private  TypeService typeService;


@PostMapping("/search")
public String search(Pageable pageable,String query,Model model){
    model.addAttribute("page", blogService.listBlog("%" + query + "%", pageable));
    model.addAttribute("query", query);
    return "search";
}


@GetMapping("/")
public String index(Pageable pageable,Model model){
    model.addAttribute("page", blogService.listBlog(pageable));
    model.addAttribute("types", typeService.listTypeTop(5));
    model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
    blogService.listBlog(pageable).getNumber();
    // System.out.println("================================");
    // System.out.println(blogService.listBlog(pageable).getContent());
    // System.out.println("================================");
    return "index";
}


@GetMapping("/blog/{id}")
public String blog(Long id,Model model){
    model.addAttribute("blog", blogService.getAndConvert(id));
    return "blog";
}


}